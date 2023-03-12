package com.iiita.placementportal.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlreadyExistsException extends RuntimeException {
    String userEmail;
    Long jobId;

    public AlreadyExistsException(String userEmail,Long jobId){
        super(String.format("User : %s has already applied for JobOpening : %s ",userEmail,jobId));
        this.userEmail = userEmail;
        this.jobId = jobId;
    }
}
