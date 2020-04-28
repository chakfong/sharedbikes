package com.fly.bikes.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "track")
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trackId")
    private Long trackId;

    @Column(name = "userId", nullable = false)
    private Long userId;

    @Column(name = "bikeId", nullable = false)
    private Long bikeId;

    @Column(name = "originLat")
    private Double originLat;

    @Column(name = "originLng")
    private Double originLng;

    @Column(name = "destinationLat")
    private Double destinationLat;

    @Column(name = "destinationLng")
    private Double destinationLng;

    @Column(name = "duration")
    private Integer duration;

    @CreatedDate
    @Column(name = "beginTime")
    private Date beginTime;

    @Column(name = "status")
    private Integer status;

    public enum TrackStatus {

        Default(0, "default", "default"),

        Tracking(1, "tracking", "The data of the track is uncompleted"),

        Completed(2, "completed", "The data of the track is completed"),

        Cancel(3, "cancel", "The track is canceled");

        private Integer code;
        private String status;
        private String description;

        TrackStatus(Integer code, String status, String description) {
            this.code = code;
            this.status = status;
            this.description = description;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    public Long getTrackId() {
        return trackId;
    }

    public void setTrackId(Long trackId) {
        this.trackId = trackId;
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

    public Double getOriginLat() {
        return originLat;
    }

    public void setOriginLat(Double originLat) {
        this.originLat = originLat;
    }

    public Double getOriginLng() {
        return originLng;
    }

    public void setOriginLng(Double originLng) {
        this.originLng = originLng;
    }

    public Double getDestinationLat() {
        return destinationLat;
    }

    public void setDestinationLat(Double destinationLat) {
        this.destinationLat = destinationLat;
    }

    public Double getDestinationLng() {
        return destinationLng;
    }

    public void setDestinationLng(Double destinationLng) {
        this.destinationLng = destinationLng;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Track{" +
                "trackId=" + trackId +
                ", userId=" + userId +
                ", bikeId=" + bikeId +
                ", originLat=" + originLat +
                ", originLng=" + originLng +
                ", destinationLat=" + destinationLat +
                ", destinationLng=" + destinationLng +
                ", duration=" + duration +
                ", beginTime=" + beginTime +
                ", status=" + status +
                '}';
    }
}
