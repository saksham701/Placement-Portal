package com.iiita.placementportal.dtos;

import com.iiita.placementportal.interviewscheduler.InterviewPlan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterviewSchedulesRequestDto {
    private List<InterviewPlan> interviewPlanList;
}
