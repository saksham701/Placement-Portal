package com.iiita.placementportal.interviewscheduler;


import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class InterviewSchdeuler {
    // what do we actually need?

    public static List<Interval> invertIntervals(List<Interval> intervals, List<Interval> plans) {
        List<Interval> availableIntervals = new ArrayList<>();

        // Sort intervals by start time
        intervals.sort(Comparator.comparing(i -> i.getStart()));

        int id = 0;

        for(Interval plan : plans){
            LocalDateTime startTime = plan.getStart();

            while(id < intervals.size()){
                Interval interval = intervals.get(id);
                if(plan.getEnd().isBefore(interval.getStart())){
                    if(!startTime.isAfter(plan.getEnd())){
                        availableIntervals.add(new Interval(startTime, plan.getEnd()));
                    }
                    break;
                }
                if(startTime.isBefore(interval.getStart())){
                    availableIntervals.add(new Interval(startTime, interval.getStart().minusMinutes(1)));
                }
                startTime = interval.getEnd().plusMinutes(1);
                id++;
            }
            if(!startTime.isAfter(plan.getEnd())){
                availableIntervals.add(new Interval(startTime, plan.getEnd()));
            }
        }

        return availableIntervals;

    }

    static class Schedule{
        Integer interviewerID;
        Interval interval;

        public Schedule(Integer interviewerID, Interval interval) {
            this.interviewerID = interviewerID;
            this.interval = interval;
        }
    }
    public static Map<String, Schedule> scheduleHelper(
            List<InterviewPlan> interviewPlanList,
            Map<String, List<Interval>> studentsAvailableIntervals
    ){
        // The smallest unit of time is considered here is 1 minute
        Map<String, Schedule> schedule = new HashMap<>();
        // student, interviewer and so on..
        List<List<List<Interval>>> interviewersAvailabilityOverPlans = new ArrayList<>();

        for(InterviewPlan plan : interviewPlanList){
            LocalDate date = plan.getDate();
            Integer duration = plan.getInterviewDuration();
            Integer interviewers = plan.getNumberOfInterviewers();
            Interval interval = plan.getInterval();
            List<List<Interval>> interviewersAvailability = new ArrayList<>();
            Integer DELTA = 1, ALPHA = 0 ;//(int) (0.15*duration);
            for(int i = 0; i < interviewers; i++){
                interviewersAvailability.add(Arrays.asList(interval));
            }
            // now, what we do, for each interviewer, we get their available period
            while(true){
                Boolean scheduled = false;
                for(int i = 0; i < interviewers; i++){
                    List<Interval> availability = interviewersAvailability.get(i);
                    if(availability.size() == 0)continue;
                    Interval lastAvailability = availability.get(availability.size() - 1);
                    LocalDateTime start = lastAvailability.getStart();
                    LocalDateTime end = lastAvailability.getEnd();
                    LocalDateTime cur = start;
                    while(cur.isBefore(end)){
                        // we have alpha amount of threshold
                        // for every minute there, check if we can find student or not
                        if(cur.plusMinutes(duration).isAfter(end))break;
                        Boolean found = false;
                        for(int j = 0; j <= ALPHA; j++){
                            cur = cur.plusMinutes(j*DELTA);
                            if(cur.plusMinutes(duration).isAfter(end))break;
                            Integer candidateFutureAvailability = 100000;
                            String candidate = "";
                            for(String student : studentsAvailableIntervals.keySet()){
                                Boolean possible = false;
                                Integer futureAvailability = 0;
                                for(Interval timeslot : studentsAvailableIntervals.get(student)){
                                    if(!cur.plusMinutes(duration).isAfter(timeslot.getEnd()) && !timeslot.getStart().isAfter(cur)){
                                        // we have a possible candidate here we time starting from cur and we take minimum
                                        possible = true;
                                    }
                                    if(timeslot.getStart().isAfter(cur) && !timeslot.getStart().plusMinutes(duration).isAfter(timeslot.getEnd())){
                                        futureAvailability++;
                                    }
                                }
                                if(possible && futureAvailability < candidateFutureAvailability){
                                    candidateFutureAvailability = futureAvailability;
                                    candidate = student;
                                }
                            }
                            if(candidate.equals("") == false){
                                schedule.put(candidate, new Schedule(i, new Interval(cur, cur.plusMinutes(duration))));
                                studentsAvailableIntervals.remove(candidate);
                                found = true;
                                System.out.println(cur + " " + cur.plusMinutes(duration));
                                if(availability.size() > 0){
                                    List<Interval> newAvailability = new ArrayList<>(availability);
                                    newAvailability.remove(newAvailability.size() - 1);
                                    // curminus 1 minute
                                    if(!start.isAfter(cur.minusMinutes(1))){
                                        newAvailability.add(new Interval(start, cur.minusMinutes(1)));
                                    }
                                    if(!cur.plusMinutes(duration + 1).isAfter(end)){
                                        newAvailability.add(new Interval(cur.plusMinutes(duration + 1), end));
                                    }
                                    interviewersAvailability.set(i, newAvailability);

                                }
                                break;

                                // change interviewer
                                // we have found a suitable candidate, now update its schedule
                            }
                        }
                        if(found){
                            scheduled = true;
                            break;
                        }
                        cur = cur.plusMinutes(DELTA);
                    }
                }
                if(!scheduled)break;
            }
            interviewersAvailabilityOverPlans.add(interviewersAvailability);
        }

        if(studentsAvailableIntervals.size() == 0){
            System.out.println("\nDONE\n");
            return schedule;
        }
        // remaining students with conflict, assign each interviewer..
        while (true){
            Boolean scheduledhere = false;
            for(int i = 0; i < interviewersAvailabilityOverPlans.size(); i++){
                List<List<Interval>> interviewersAvailability = interviewersAvailabilityOverPlans.get(i);
                for(int j = 0; j < interviewersAvailability.size(); j++){
                    // j is interviewer id here
                    Boolean done = false;
                    for(Interval interval : interviewersAvailability.get(j)){
                        LocalDateTime start = interval.getStart();
                        LocalDateTime end = interval.getEnd();
                        Integer duration = interviewPlanList.get(i).getInterviewDuration();
                        if(!start.plusMinutes(duration).isAfter(end)){
                            // just get the first student and assign that mf this value and change the availability...
                            // get first student to this thing, simply assign them values after ending period....
                            String candidate = studentsAvailableIntervals.entrySet().iterator().next().getKey();
                            schedule.put(candidate, new Schedule(j, new Interval(start, start.plusMinutes(duration))));
                            studentsAvailableIntervals.remove(candidate);
                            done = true;
                            List<Interval> newAvailability = new ArrayList<>(interviewersAvailability.get(j));
                            newAvailability.remove(interval);
                            if(!start.plusMinutes(duration + 1).isAfter(end)){
                                newAvailability.add(new Interval(start.plusMinutes(duration+1), end));
                                newAvailability.sort(Comparator.comparing(interval1 -> interval1.getStart()));
                            }
                            // change to new availability
                            interviewersAvailability.set(j, newAvailability);
                            interviewersAvailabilityOverPlans.set(i, interviewersAvailability);
                            // change availability
                            break;
                        }
                        if(done){
                            scheduledhere = true;
                            break;
                        }
                    }
                }
            }
            if(!scheduledhere)break;
        }

        if(studentsAvailableIntervals.size() == 0){
            System.out.println("\nDONE\n");
            return schedule;
        }

        // remaining students, assign after the plans time period ..

        Integer cnt = studentsAvailableIntervals.size();
        Integer perplancnt = cnt/interviewPlanList.size();
        for(int ip = 0; ip < interviewPlanList.size(); ip++){
            Interval interval = interviewPlanList.get(ip).getInterval();
            Integer interviewers = interviewPlanList.get(ip).getNumberOfInterviewers();
            Integer processStudents = perplancnt;
            if(ip == interviewPlanList.size() - 1){
                processStudents = studentsAvailableIntervals.size();
            }
            Integer k = processStudents/interviewers;
            Integer duration = interviewPlanList.get(ip).getInterviewDuration();
            LocalDateTime cur = interval.getEnd().plusMinutes(1);
            for(int i = 0; i < interviewers; i++){
//                    i = interviewer id
                Integer processStudentsFor = k;
                if(i == interviewers - 1){
                    processStudentsFor = processStudents;
                }
                while(processStudentsFor > 0){
                    if(studentsAvailableIntervals.size() > 0){
                        String candidate = studentsAvailableIntervals.entrySet().iterator().next().getKey();
                        schedule.put(candidate, new Schedule(i, new Interval(cur, cur.plusMinutes(duration))));
                        studentsAvailableIntervals.remove(candidate);
                        cur = cur.plusMinutes(duration+1);
                    }
                    processStudentsFor--;
                }
                processStudents -= processStudentsFor;
            }
        }
        return schedule;
    }

    public static void schedule(
            List<InterviewPlan> interviewPlanList,
            Map<String, List<Interval>> studentsPreScheduledInterviews
    ){
//        System.out.println("\n\nStarting\n\n");
        Map<String, List<Interval>> studentsAvailableIntervals = new HashMap<>();
        interviewPlanList.sort(Comparator.comparing(ip -> ip.getDate()));
        List<Interval> plans = new ArrayList<>();
        for(InterviewPlan ip : interviewPlanList){
            plans.add(ip.getInterval());
        }

        for(String studentId : studentsPreScheduledInterviews.keySet()){
            studentsAvailableIntervals.put(studentId, invertIntervals(studentsPreScheduledInterviews.get(studentId), plans));
        }
//        System.out.println("\n\nSuccess");
//        for(Interval interval:studentsAvailableIntervals.get("rana")){
//            System.out.println(interval.getStart() + " " + interval.getEnd());
//        }
        System.out.println(studentsAvailableIntervals);
//       NOTE - TODO - THIS is the final answer = student id, schedule - interviewer id, interval of interview..
        Map<String, Schedule> schedule = scheduleHelper(interviewPlanList, studentsAvailableIntervals);
        for(String student : schedule.keySet()){
            System.out.println("STUDENT : " + student);
            Schedule obj = schedule.get(student);
            System.out.println("INTERVIEWER " + obj.interviewerID);
            System.out.println("INTERVAL " + obj.interval.getStart() + " " + obj.interval.getEnd());
        }
        // DO SOMETHING NOW FOR SCHEDULING, can be done but how can we do this
        // TODO - CREATE POSSIBLE RESPONSE WHICH CAN BE SEND OVER - ALGOrithm is done
    }
}

