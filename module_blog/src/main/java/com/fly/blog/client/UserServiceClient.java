package com.fly.blog.client;

import com.fly.blog.entity.User;
import com.fly.blog.client.hystrix.UserServiceHystrix;
import com.fly.common.dto.RespDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;




@FeignClient(value = "user-service",fallback = UserServiceHystrix.class )
public interface UserServiceClient {

    @PostMapping(value = "/user/{username}")
    RespDTO<User> getUser(@RequestHeader(value = "Authorization") String token, @PathVariable("username") String username);
}



