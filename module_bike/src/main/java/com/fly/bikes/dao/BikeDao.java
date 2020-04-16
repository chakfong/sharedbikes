package com.fly.bikes.dao;

import com.fly.bikes.entity.Bike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BikeDao extends JpaRepository<Bike, Long> {

    Bike findByBikeId(Long bikeId);

    List<Bike> findByGeoHashLike(String geoHash);

    @Modifying
    @Query(value = "update bike b set b.status = 2 where b.bike_id = ?1 and b.status = 1")
    Integer  setLeaseStatus(Long bikeId);
}
