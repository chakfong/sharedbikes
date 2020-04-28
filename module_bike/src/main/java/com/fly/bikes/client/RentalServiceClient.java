package com.fly.bikes.client;

import com.fly.bikes.client.hystrix.RentalServiceHystrix;
import com.fly.common.dto.RespDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "rental-service", fallback = RentalServiceHystrix.class)
public interface RentalServiceClient {

    @GetMapping(value = "/rental/lease")
    RespDTO lease(@RequestParam("bikeId") Long bikeId,
            @RequestParam("userId") Long userId,
            @RequestParam("lat") Double lat,
            @RequestParam("lng") Double lng);
}
