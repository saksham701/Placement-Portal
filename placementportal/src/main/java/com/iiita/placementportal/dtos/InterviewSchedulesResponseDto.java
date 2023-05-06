package com.iiita.placementportal.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterviewSchedulesResponseDto {
    private String studentEmail;
    private Integer interviewerId;
    private LocalDateTime start;
    private LocalDateTime end;
}
