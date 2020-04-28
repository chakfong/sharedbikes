package com.fly.bikes.service;


import com.fly.bikes.client.RentalServiceClient;
import com.fly.bikes.dao.BikeDao;
import com.fly.bikes.entity.Bike;
import com.fly.bikes.entity.Track;
import com.fly.bikes.util.GeoUtils;
import com.fly.common.dto.RespDTO;
import com.fly.common.exception.CommonException;
import com.fly.common.exception.ErrorCode;
import com.fly.common.utils.ObjectMapperUtil;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.distance.DistanceUtils;
import org.locationtech.spatial4j.io.GeohashUtils;
import org.redisson.client.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class BikeService {

    @Autowired
    BikeDao bikeDao;

    @Autowired
    RentalServiceClient rentalServiceClient;

    private SpatialContext spatialContext = SpatialContext.GEO;

    public Bike createBike(Bike bike) {
        String geoHashCode = GeohashUtils.encodeLatLon(bike.getLat(), bike.getLng());
        bike.setGeoHash(geoHashCode);
        return bikeDao.save(bike);
    }

    public List<Bike> createBikes(List<Bike> bikes) {
        for (Bike bike : bikes) {
            bike.setGeoHash(GeohashUtils.encodeLatLon(bike.getLat(), bike.getLng()));
        }
        return bikeDao.save(bikes);
    }

    public Bike findById(Long bikeId) {
        return bikeDao.findOne(bikeId);
    }

    public List<Bike> nearbySearch(Double lat, Double lng, Integer len) {
        String geoHashCode = GeohashUtils.encodeLatLon(lat, lng, len);
        System.out.println(geoHashCode);
        String[] geoHashs = GeoUtils.suroundingLocation(geoHashCode);

        List<Bike> bikes = new ArrayList<>();
        for(int i=0;i<geoHashs.length;i++){
            bikes.addAll(bikeDao.findByGeoHashLike(geoHashs[i]+"%"));
        }
        bikes.stream().forEach(System.out::println);
//        List<Bike> bikes = bikeDao.findByGeoHashLike(geoHashCode + "%");

        double distance = 1;
        return bikes.stream()
                .filter(bike -> getDistance(bike.getLng(), bike.getLat(), lng, lat) <= distance)
                .collect(Collectors.toList());
    }

    public boolean leasable(Bike bike) {
//        return Bike.BikeStatus.Available.getValue().equals(bike.getStatus());
        return Bike.BikeStatus.get(bike.getStatus()).equals(Bike.BikeStatus.Available);
    }

    @Transactional
    public Track lease(Bike bike, Long userId) {
        Integer result = bikeDao.setLeaseStatus(bike.getBikeId());
        if (result == 0)
            throw new CommonException(ErrorCode.BIKE_IS_NOT_AVAILABLE);
        RespDTO resp = rentalServiceClient.lease(bike.getBikeId(), userId, bike.getLat(), bike.getLng());
        resp.checkResponse();
        return ObjectMapperUtil.convertValue(resp.data, Track.class);
    }

    @Transactional
    public Bike lockBike(Long bikeId){
        Integer result = bikeDao.setLockStatus(bikeId);
        if (result == 0)
            throw new CommonException(ErrorCode.BIKE_IS_NOT_EXIST);
        Bike bike = bikeDao.findByBikeId(bikeId);
        return bike;
    }

    private double getDistance(Double longitude, Double latitude, double userLng, double userLat) {
        return spatialContext.calcDistance(spatialContext.makePoint(userLng, userLat),
                spatialContext.makePoint(longitude, latitude)) * DistanceUtils.DEG_TO_KM;
    }
}
