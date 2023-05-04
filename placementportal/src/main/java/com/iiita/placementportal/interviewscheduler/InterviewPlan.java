package com.iiita.placementportal.interviewscheduler;

import java.time.LocalDate;

public class InterviewPlan{
    // we can have multiple plans, so date and time should be the case
    private LocalDate date;
    private Interval interval;
    private Integer numberOfInterviewers;
    private Integer interviewDuration;

    public InterviewPlan(LocalDate date, Interval interval, Integer numberOfInterviewers, Integer interviewDuration) {
        this.date = date;
        this.interval = interval;
        this.numberOfInterviewers = numberOfInterviewers;
        this.interviewDuration = interviewDuration;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Interval getInterval() {
        return interval;
    }

    public void setInterval(Interval interval) {
        this.interval = interval;
    }

    public Integer getNumberOfInterviewers() {
        return numberOfInterviewers;
    }

    public void setNumberOfInterviewers(Integer numberOfInterviewers) {
        this.numberOfInterviewers = numberOfInterviewers;
    }

    public Integer getInterviewDuration() {
        return interviewDuration;
    }

    public void setInterviewDuration(Integer interviewDuration) {
        this.interviewDuration = interviewDuration;
    }
}
