package com.fly.rental.client.hystrix;

import com.fly.common.dto.LocationDTO;
import com.fly.rental.client.BikeServiceClient;
import com.fly.common.dto.RespDTO;
import com.fly.common.exception.ErrorCode;
import org.springframework.stereotype.Component;

@Component
public class BikeServiceHystrix implements BikeServiceClient {

    @Override
    public RespDTO getBike(Long bikeId) {
        return RespDTO.onError(ErrorCode.RPC_ERROR);
    }

    @Override
    public RespDTO getBikeLocation(Long bikeId) {
        return RespDTO.onError(ErrorCode.RPC_ERROR);
    }

    @Override
    public RespDTO lockBike(Long bikeId) {
        return RespDTO.onError(ErrorCode.RPC_ERROR);
    }
}
