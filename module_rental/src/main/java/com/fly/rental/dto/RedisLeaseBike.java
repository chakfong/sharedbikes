package com.fly.rental.dto;

import java.util.Date;

public class RedisLeaseBike {
    private Long userId;
    private Long bikeId;
    private Date beginTime;
    private Date endTime;

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public RedisLeaseBike(Long userId, Long bikeId, Date beginTime) {
        this.userId = userId;
        this.bikeId = bikeId;
        this.beginTime = beginTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBikeId() {
        return bikeId;
    }

    public void setBikeId(Long bikeId) {
        this.bikeId = bikeId;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }
}
