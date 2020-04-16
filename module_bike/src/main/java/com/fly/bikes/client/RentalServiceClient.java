package com.fly.bikes.client;

import com.fly.bikes.client.hystrix.RentalServiceHystrix;
import com.fly.common.dto.RespDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "rental-service", fallback = RentalServiceHystrix.class)
public interface RentalServiceClient {

    @PostMapping(value = "/rental/lease")
    RespDTO<Object> lease(@PathVariable("bikeId") Long bikeId, @PathVariable("userId") Long userId);
}
