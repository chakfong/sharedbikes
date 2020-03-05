package com.fly.bikes.web;


import com.fly.bikes.entity.Bike;
import com.fly.bikes.service.BikeService;
import com.fly.common.dto.RespDTO;
import io.swagger.annotations.ApiOperation;
import org.locationtech.spatial4j.io.GeohashUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public RespDTO getBikesNearby(@RequestParam Double lat,@RequestParam Double lng){
        List<Bike> bikes = bikeService.nearbySearch(lat,lng,6);
        return RespDTO.onSuc(bikes);
    }
}
