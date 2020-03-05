package com.fly.bikes.dao;

import com.fly.bikes.entity.Bike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BikeDao extends JpaRepository<Bike, Long> {

    Bike findByBikeId(Long bikeId);

    List<Bike> findByGeoHashLike(String geoHash);
}
