package com.fly.user.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fly.common.exception.CommonException;
import com.fly.common.exception.ErrorCode;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class OkHttpUtils {
    public static JSONObject get(String url){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            String responseString = response.body().string();
            return JSON.parseObject(responseString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new CommonException(ErrorCode.OKHTTP_ERROR);
    }
}
