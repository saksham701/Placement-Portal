package com.iiita.placementportal;

import com.iiita.placementportal.interviewscheduler.Interval;
import com.iiita.placementportal.interviewscheduler.InterviewPlan;
import com.iiita.placementportal.interviewscheduler.InterviewSchdeuler;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@SpringBootApplication
public class PlacementportalApplication {

	public static void main(String[] args) {
		List<InterviewPlan> interviewPlanList = new ArrayList<>();
		// we can have localtime as well, its fine no problem
		LocalDate localDate = LocalDate.of(2023, 5, 3);
		Interval interval = new Interval(LocalDateTime.of(localDate, LocalTime.of(12, 0)),
				LocalDateTime.of(localDate, LocalTime.of(15, 0)));

		interviewPlanList.add(new InterviewPlan(localDate, interval, 2, 40));
		interval = new Interval(LocalDateTime.of(localDate.plusDays(1), LocalTime.of(12, 0)),
				LocalDateTime.of(localDate.plusDays(1), LocalTime.of(15, 0)));
		interviewPlanList.add(new InterviewPlan(localDate.plusDays(1), interval, 2, 40));
		Map<String, List<Interval>> mp = new HashMap<>();
		mp.put("rana", Arrays.asList(new Interval(LocalDateTime.of(localDate, LocalTime.of(13, 0)),
				LocalDateTime.of(localDate, LocalTime.of(14, 0)))
				));
		mp.put("rana_2", Arrays.asList(new Interval(LocalDateTime.of(localDate, LocalTime.of(12, 0)),
				LocalDateTime.of(localDate, LocalTime.of(14, 0)))
		));
		InterviewSchdeuler.schedule(interviewPlanList, mp);

//		SpringApplication.run(PlacementportalApplication.class, args);

	}
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}


}
