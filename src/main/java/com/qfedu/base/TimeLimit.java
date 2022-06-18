package com.qfedu.base;

import javax.persistence.Transient;
import java.util.Date;

public class TimeLimit {
    @Transient
    private Date startTime;
    @Transient
    private Date endTime;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
