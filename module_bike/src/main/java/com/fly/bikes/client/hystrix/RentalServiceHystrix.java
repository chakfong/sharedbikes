package com.fly.bikes.client.hystrix;

import com.fly.bikes.client.RentalServiceClient;
import com.fly.common.dto.RespDTO;
import com.fly.common.exception.ErrorCode;
import org.springframework.stereotype.Component;

@Component
public class RentalServiceHystrix implements RentalServiceClient {

    @Override
    public RespDTO<Object> lease(Long bikeId, Long userId, Double lat, Double lng) {
        return RespDTO.onError(ErrorCode.RPC_ERROR);
    }
}
