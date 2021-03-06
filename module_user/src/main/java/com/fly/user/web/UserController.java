package com.fly.user.web;

import com.fly.common.annotation.SysLogger;
import com.fly.common.dto.RespDTO;
import com.fly.user.dto.request.UserLoginReq;
import com.fly.user.entity.User;
import com.fly.user.service.UserService;
import com.fly.user.util.BPwdEncoderUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "注册", notes = "username和password为必选项")
    @PostMapping("/registry")
//    @SysLogger("registry")
    public User createUser(@RequestBody User user){
        //参数判读省略,判读该用户在数据库是否已经存在省略
        String entryPassword= BPwdEncoderUtils.BCryptPassword(user.getPassword());
        user.setPassword(entryPassword);
        return userService.createUser(user);
    }

    @ApiOperation(value = "登录", notes = "username和password为必选项")
    @PostMapping("/login")
//    @SysLogger("login")
    public RespDTO login(@RequestBody UserLoginReq userLoginReq){
        //参数判读省略
      return   userService.login(userLoginReq.getUsername(),userLoginReq.getPassword());
    }

    @ApiOperation(value = "小程序微信登录", notes = "小程序微信登陆")
    @PostMapping("/wxlogin")
    public RespDTO wxLogin(@RequestParam String code){
        //参数判读省略
        return RespDTO.onSuc(userService.wxLogin(code));
    }

    @ApiOperation(value = "根据用户名获取用户", notes = "根据用户名获取用户")
    @PostMapping("/{username}")
    @PreAuthorize("hasRole('USER')")
    @SysLogger("getUserInfo")
   // @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    public RespDTO getUser(@PathVariable("username") String username){
        //参数判读省略
        User user=  userService.getUserInfo(username);
        return RespDTO.onSuc(user);
    }
}
