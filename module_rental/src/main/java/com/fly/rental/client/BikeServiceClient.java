package com.fly.rental.client;

import com.fly.common.dto.LocationDTO;
import com.fly.rental.client.hystrix.BikeServiceHystrix;
import com.fly.common.dto.RespDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "bike-service", fallback = BikeServiceHystrix.class)
public interface BikeServiceClient {

    @GetMapping(value = "/bike/{bikeId}")
    RespDTO getBike(@PathVariable("bikeId") Long bikeId);

    @GetMapping(value = "/bike/location")
    RespDTO getBikeLocation(@RequestParam(value = "bikeId") Long bikeId);

    @PostMapping(value = "/bike/lock")
    RespDTO lockBike(@RequestParam(value = "bikeId") Long bikeId);
}
