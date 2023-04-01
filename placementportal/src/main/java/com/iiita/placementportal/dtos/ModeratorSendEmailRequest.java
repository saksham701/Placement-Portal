package com.iiita.placementportal.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModeratorSendEmailRequest {
    private Long jobId;
    private String status;
    private String subject;
    private String message;
}
