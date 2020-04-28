package com.fly.rental.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productId")
    private Long productId;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Long price;

    @CreatedDate
    @Column(name = "createTime")
    private Date createTime;

    @Column(name = "status")
    private Integer status;

    public enum ProductStatus {

        Default(0, "default", "default"),

        ON(1, "reserve", ""),

        OFF(2, "completed", ""),

        Cancel(3, "cancel", "");

        private Integer code;
        private String status;
        private String description;

        ProductStatus(Integer code, String status, String description) {
            this.code = code;
            this.status = status;
            this.description = description;
        }
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
