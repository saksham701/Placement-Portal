package com.iiita.placementportal.dtos;

import com.iiita.placementportal.interviewscheduler.Schedule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveSchdeulesRequestDto {
    private List<InterviewSchedulesResponseDto> scheduleList;
}
