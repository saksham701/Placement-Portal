package com.iiita.placementportal.interviewscheduler;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Schedule {
    Integer interviewerID;
    Interval interval;

    public Schedule(Integer interviewerID, Interval interval) {
        this.interviewerID = interviewerID;
        this.interval = interval;
    }

}
