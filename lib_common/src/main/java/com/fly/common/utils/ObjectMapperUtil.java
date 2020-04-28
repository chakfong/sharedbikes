package com.fly.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fly.common.exception.CommonException;

public class ObjectMapperUtil {

    private static volatile ObjectMapper objectMapper;

    private static ObjectMapper getInstance(){
        if(objectMapper == null){
            synchronized(ObjectMapper.class){
                if(objectMapper == null){
                    objectMapper = new ObjectMapper();
                }
            }
        }
        return objectMapper;
    }
    public static <T,K> K convertValue(T fromValue,Class<K> toValueType){
        ObjectMapper mapper = ObjectMapperUtil.getInstance();

        return mapper.convertValue(fromValue, toValueType);
    }
}
