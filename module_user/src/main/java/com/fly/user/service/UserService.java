package com.fly.user.service;

import com.alibaba.fastjson.JSONObject;
import com.fly.common.dto.RespDTO;
import com.fly.common.exception.CommonException;
import com.fly.common.exception.ErrorCode;
import com.fly.user.dao.UserDao;
import com.fly.user.dto.LoginDTO;
import com.fly.user.entity.JWT;
import com.fly.user.entity.User;
import com.fly.user.client.AuthServiceClient;
import com.fly.user.util.BPwdEncoderUtils;
import com.fly.user.util.OkHttpUtils;
import okhttp3.OkHttpClient;
import org.bouncycastle.jcajce.provider.asymmetric.rsa.RSAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.bootstrap.encrypt.KeyProperties;
import org.springframework.cloud.commons.httpclient.OkHttpClientFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Description: <UserService><br>
 * Author:    mxdl<br>
 * Date:      2019/2/19<br>
 * Version:    V1.0.0<br>
 * Update:     <br>
 */
@Service
public class UserService {

    @Autowired
    public
    UserDao userDao;
    @Autowired
    AuthServiceClient authServiceClient;
    @Value("${wx.appid}")
    String appid;
    @Value("${wx.secret}")
    String secret;


    private String LOGIN_TOKEN = "SharedBikes_T8BAKL2R0_";

    public User createUser(User user) {
        return userDao.save(user);
    }

    public User getUserInfo(String username) {
        return userDao.findByUsername(username);
    }

    public User wxLogin(String code) {
        JSONObject jsonObject = OkHttpUtils.get("https://api.weixin.qq.com/sns/jscode2session" +
                "?appid=" + appid +
                "&secret=" + secret +
                "&js_code=" + code +
                "&grant_type=authorization_code");
        String openid = jsonObject.get("openid").toString();
        User user = userDao.findUserByWxOpenIdIs(openid);
        if(user == null){
            user = new User();
            user.setWxOpenId(openid);
            user.setUsername(UUID.randomUUID().toString().replace("-",""));
            user.setIsLock(2);
            user = userDao.save(user);
        }
        return user;
    }

    public RespDTO login(String username, String password) {
        User user = userDao.findByUsername(username);
        if (null == user) {
            throw new CommonException(ErrorCode.USER_NOT_FOUND);
        }
        if (!BPwdEncoderUtils.matches(password, user.getPassword())) {
            throw new CommonException(ErrorCode.USER_PASSWORD_ERROR);
        }

//        JWT jwt = authServiceClient.getToken("Basic dWFhLXNlcnZpY2U6MTIzNDU2", "password", username, password);
//        // 获得用户菜单
//        if(null==jwt){
//            throw new CommonException(ErrorCode.GET_TOKEN_FAIL);
//        }

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUser(user);
//        loginDTO.setToken(jwt.getAccess_token());
        loginDTO.setToken(BPwdEncoderUtils.BCryptPassword(LOGIN_TOKEN + username));
        return RespDTO.onSuc(loginDTO);
    }
}
