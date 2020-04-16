package com.fly.bikes.web;


import com.fly.bikes.entity.Bike;
import com.fly.bikes.service.BikeService;
import com.fly.common.configuration.RedissonConfig;
import com.fly.common.dto.RespDTO;
import io.swagger.annotations.ApiOperation;
import org.locationtech.spatial4j.io.GeohashUtils;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/bike")
public class BikeController {

    @Autowired
    BikeService bikeService;

    @ApiOperation(value = "投放车辆", notes = "投放车辆")
//    @PreAuthorize("hasRole('USER')")
    @PostMapping("/post")
//    @SysLogger("postBike")
    public RespDTO postBike(@RequestBody Bike bike) {
        //字段判读省略
        Bike bike1 = bikeService.createBike(bike);
        return RespDTO.onSuc(bike1);
    }

    @ApiOperation(value = "模拟单车投放", notes = "模拟单车投放")
    @GetMapping("/launch")
    public RespDTO launchBike(@RequestBody Bike bike) {
        List<Bike> bikes = new ArrayList<>();
        for(int i = 0;i < 10; i++){
            Integer range = 6;
            Double randomLat = range * Math.random() / 10000;
            Double randomLong = range * Math.random() / 1000;
            bike.setLat(randomLat - (range / 20000));
            bike.setLng(randomLong - (range / 2000));
            bike.setPassword(String.valueOf(Math.round(1000 + Math.random() * 9000)));
            bikeService.createBike(bike);
            bikes.add(bike);
        }
        return RespDTO.onSuc(bikes.toString());
    }

    @ApiOperation(value = "根据id获取单车", notes = "根据id获取单车")
//    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/{bikeId}")
//    @SysLogger("getBike")
    public RespDTO getBike(@PathVariable Long bikeId) {
        //字段判读省略
        Bike bike = bikeService.findById(bikeId);
        System.out.println(bikeId);
        System.out.println(bike);
        return RespDTO.onSuc(bike);
    }

    @ApiOperation(value = "根据经纬度获取附近单车", notes = "根据经纬度获取附近单车")
    @GetMapping("/nearby")
    public RespDTO getBikesNearby(@RequestParam Double lat, @RequestParam Double lng) {
        List<Bike> bikes = bikeService.nearbySearch(lat, lng, 6);
        return RespDTO.onSuc(bikes);
    }

    @ApiOperation(value = "获取单车密码并开始租赁", notes = "获取单车密码并开始租赁")
    @GetMapping("/lease")
    public RespDTO leaseBike(@RequestParam Long userId, @RequestParam Long bikeId) {
        Bike bike = bikeService.findById(bikeId);
        if(!bikeService.leasable(bike)){
            return RespDTO.onSuc("该共享单车正常使用中。");
        }
        bikeService.lease(bike,userId);
        return RespDTO.onSuc(bike.getPassword());
    }
}
