package com.fly.bikes.service;


import com.fly.bikes.dao.BikeDao;
import com.fly.bikes.entity.Bike;
import com.fly.common.dto.RespDTO;
import com.fly.common.exception.CommonException;
import com.fly.common.exception.ErrorCode;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.distance.DistanceUtils;
import org.locationtech.spatial4j.io.GeohashUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class BikeService {

    @Autowired
    BikeDao bikeDao;

    private SpatialContext spatialContext = SpatialContext.GEO;

    public Bike createBike(Bike bike) {
        String geoHashCode = GeohashUtils.encodeLatLon(bike.getLat(), bike.getLng());
        bike.setGeoHash(geoHashCode);
        return bikeDao.save(bike);
    }

    public Bike findById(Long bikeId) {
        return bikeDao.findOne(bikeId);
    }

    public List<Bike> nearbySearch(Double lat, Double lng, Integer len) {
        String geoHashCode = GeohashUtils.encodeLatLon(lat, lng, len);
        System.out.println(geoHashCode);
        List<Bike> bikes = bikeDao.findByGeoHashLike(geoHashCode + "%");
        bikes.stream().forEach(System.out::println);
        double distance = 1;
        return bikes.stream()
                .filter(bike -> getDistance(bike.getLng(), bike.getLat(), lng, lat) <= distance)
                .collect(Collectors.toList());
    }

    private double getDistance(Double longitude, Double latitude, double userLng, double userLat) {
        return spatialContext.calcDistance(spatialContext.makePoint(userLng, userLat),
                spatialContext.makePoint(longitude, latitude)) * DistanceUtils.DEG_TO_KM;
    }
}
