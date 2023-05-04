package com.iiita.placementportal.interviewscheduler;

import org.hibernate.engine.jdbc.SerializableBlobProxy;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Interval {
    private LocalDateTime start, end;

    public Interval(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

}

