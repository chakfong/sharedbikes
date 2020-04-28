package com.fly.common.dto;

import com.fly.common.exception.CommonException;
import com.fly.common.exception.ErrorCode;

import java.io.Serializable;

/**
 * Description: <RespDTO><br>
 * Author:    mxdl<br>
 * Date:      2019/2/19<br>
 * Version:    V1.0.0<br>
 * Update:     <br>
 */
public class RespDTO<T> implements Serializable {


    public int code = 0;
    public String error = "";

    public T data;

    public static <T> RespDTO<T> onSuc(T data) {
        RespDTO<T> resp = new RespDTO<>();
        resp.data = data;
        return resp;
    }

    public static <T> RespDTO<T> onError(ErrorCode error) {
        RespDTO<T> resp = new RespDTO<>();
        resp.setError(error.getMsg());
        resp.setCode(error.getCode());
        return resp;
    }

    @Override
    public String toString() {
        return "RespDTO{" +
                "code=" + code +
                ", error='" + error + '\'' +
                ", data=" + data +
                '}';
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getCode() {
        return code;
    }

    public String getError() {
        return error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void checkResponse(){
        ErrorCode errorCode = ErrorCode.codeOf(code);
        if(errorCode == null)
            throw new CommonException(ErrorCode.CODE_IS_NOT_EXIST);
        if(errorCode != ErrorCode.OK)
            throw new CommonException(errorCode);
    }
}
