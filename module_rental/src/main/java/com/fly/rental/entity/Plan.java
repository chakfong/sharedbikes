package com.fly.rental.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "plan")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "planId")
    private Long planId;

    @Column(name = "userId", nullable = false)
    private Long userId;

    @Column(name = "bikeId", nullable = false)
    private Long bikeId;

    @Column(name = "beginTime", nullable = false)
    private Date beginTime;

    @Column(name = "endTime")
    private Date endTime;

    @CreatedDate
    @Column(name = "createTime")
    private Date createTime;

    @Column(name = "status")
    private Integer status;

    public enum PlanStatus {

        Default(0, "default", "default"),

        RESERVE(1, "reserve", ""),

        Completed(2, "completed", ""),

        TIMEOUT(3, "timeout", ""),

        Cancel(4, "cancel", "");

        private Integer code;
        private String status;
        private String description;

        PlanStatus(Integer code, String status, String description) {
            this.code = code;
            this.status = status;
            this.description = description;
        }
    }
    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
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

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
