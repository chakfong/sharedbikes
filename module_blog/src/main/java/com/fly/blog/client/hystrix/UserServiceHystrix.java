package com.fly.blog.client.hystrix;

import com.fly.blog.client.UserServiceClient;
import com.fly.blog.entity.User;
import com.fly.common.dto.RespDTO;
import org.springframework.stereotype.Component;


@Component
public class UserServiceHystrix implements UserServiceClient {

    @Override
    public RespDTO<User> getUser(String token, String username) {
        System.out.println(token);
        System.out.println(username);
        return null;
    }
}
