package com.fly.rental.service;

import com.fly.common.dto.LocationDTO;
import com.fly.common.dto.RespDTO;
import com.fly.common.exception.CommonException;
import com.fly.common.exception.ErrorCode;
import com.fly.common.utils.DateUtil;
import com.fly.common.utils.ObjectMapperUtil;
import com.fly.rental.client.BikeServiceClient;
import com.fly.rental.dao.ProductDao;
import com.fly.rental.dao.TrackDao;
import com.fly.rental.dao.TransactionDao;
import com.fly.rental.dto.RedisLeaseBike;
import com.fly.rental.entity.Bike;
import com.fly.rental.entity.Product;
import com.fly.rental.entity.Track;
import com.fly.rental.entity.Transaction;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RentalService {

//    @Autowired
//    RedissonClient redissonClient;
    @Autowired
    BikeServiceClient bikeServiceClient;
    @Autowired
    TrackDao trackDao;
    @Autowired
    ProductDao productDao;
    @Autowired
    TransactionDao transactionDao;

    private static String prev = "LEASE_BIKE_URSERID_";
    private static String prev2 = "BIKEID_";

    public Track leaseBike(Long userId, Long bikeId, Double lat, Double lng) {
//        RBucket<RedisLeaseBike> bucket = redissonClient.getBucket(prev + userId + prev2 + bikeId);
//        RedisLeaseBike lb = new RedisLeaseBike(userId, bikeId, beginTime);
//        bucket.set(lb);

        Date beginTime = new Date();
        Track track = new Track();
        track.setBikeId(bikeId);
        track.setUserId(userId);
        track.setBeginTime(beginTime);
        track.setStatus(Track.TrackStatus.Tracking.getCode());
//        RespDTO resp = bikeServiceClient.getBikeLocation(bikeId);
//        LocationDTO locationDTO = ObjectMapperUtil.convertValue(resp.data, LocationDTO.class);
//        System.out.println(locationDTO.toString());
        track.setOriginLat(lat);
        track.setOriginLng(lng);
        return trackDao.save(track);
    }

//    public Boolean lockBike(Long userId, Long bikeId) {
//        RBucket<RedisLeaseBike> bucket = redissonClient.getBucket(prev + userId + prev2 + bikeId);
//        RedisLeaseBike lb = bucket.get();
//        if (lb == null)
//            return false;
//        Date endTime = new Date();
//        lb.setEndTime(endTime);
//        bucket.set(lb);
//        return true;
//    }

    public Track findTrackById(Long trackId){
        Track track = trackDao.findOne(trackId);
        if(track == null)
            throw new CommonException(ErrorCode.TRACK_IS_NOT_EXIST);
        return track;
    }

    public Transaction finish(Track track,Double lat,Double lng){
        Date now = new Date();
        track.setDestinationLat(lat);
        track.setDestinationLng(lng);
        track.setStatus(Track.TrackStatus.Completed.getCode());
        track.setDuration(DateUtil.getDurationIn30Minute(track.getBeginTime(),now));
        trackDao.flush();

        // TODO 刷新单车经纬度
        RespDTO resp = bikeServiceClient.lockBike(track.getBikeId());
//        RespDTO resp = bikeServiceClient.getBike(track.getBikeId());
        resp.checkResponse();
        Bike bike = ObjectMapperUtil.convertValue(resp.data, Bike.class);

        Transaction transaction = new Transaction();
        Product bikeProduct = productDao.findOne(bike.getProductId());
        Long price = bikeProduct.getPrice();
        transaction.setAmount(track.getDuration() * price);
        transaction.setProductId(bike.getProductId());
        transaction.setStatus(Transaction.TransactionStatus.Completed.getCode());
        transaction.setTrackId(track.getTrackId());
        transaction.setUserId(track.getUserId());

        return transactionDao.save(transaction);
    }

    public void dd(){

    }
}
