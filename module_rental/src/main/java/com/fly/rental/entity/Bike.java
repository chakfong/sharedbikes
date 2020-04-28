package com.fly.rental.entity;


import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "bike")
public class Bike implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bikeId")
    private Long bikeId;

    @Column(name = "productId", nullable = false)
    private Long productId;

    @Column(name = "password")
    private String password;

    @Column(name = "lat")
    private Double lat;

    @Column(name = "lng")
    private Double lng;

    @Column(name = "geoHash")
    private String geoHash;

    @Column(name = "status")
    private Integer status;

    @CreatedDate
    @Column(name = "createTime")
    private Date createTime;

    public enum BikeStatus {

        Default(0, "default","default"),

        Available(1, "available", "The bike is ready to take"),

        Using(2, "using", "The bike is in use"),

        Cancel(3, "cancel", "The bike is canceled");

        private Integer value;
        private String status;
        private String description;

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
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

        BikeStatus(Integer value, String status, String description) {
            this.value = value;
            this.status = status;
            this.description = description;
        }

        public static BikeStatus get(Integer value){
            return Arrays.stream(BikeStatus.values())
                    .filter(bikeStatus -> bikeStatus.value.equals(value))
                    .findFirst()
                    .get();
        }
    }

    public Bike() {
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getBikeId() {
        return bikeId;
    }

    public void setBikeId(Long bikeId) {
        this.bikeId = bikeId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getGeoHash() {
        return geoHash;
    }

    public void setGeoHash(String geoHash) {
        this.geoHash = geoHash;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Bike{" +
                "bikeId=" + bikeId +
                ", productId=" + productId +
                ", password='" + password + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                ", geoHash='" + geoHash + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                '}';
    }
}
