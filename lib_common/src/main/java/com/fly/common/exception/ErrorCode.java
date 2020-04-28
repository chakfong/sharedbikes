package com.fly.common.exception;

/**
 * Description: <通用错误码><br>
 * Author:    mxdl<br>
 * Date:      2019/2/19<br>
 * Version:    V1.0.0<br>
 * Update:     <br>
 */
public enum ErrorCode {

    OK(0, ""),
    FAIL(-1, "操作失败"),
    RPC_ERROR(-2, "远程调度失败"),
    //用户微服务
    USER_NOT_FOUND(1000, "用户不存在"),
    USER_PASSWORD_ERROR(1001, "密码错误"),
    GET_TOKEN_FAIL(1002, "获取token失败"),
    TOKEN_IS_NOT_MATCH_USER(1003, "请使用自己的token进行接口请求"),

    BLOG_IS_NOT_EXIST(2001, "该内容不存在"),
    //单车微服务
    BIKE_IS_NOT_EXIST(3000, "单车不存在"),
    BIKE_IS_NOT_AVAILABLE(3001, "单车不可用"),

    //租赁微服务
    TRACK_IS_NOT_EXIST(4000, "轨迹不存在"),
    //通用
    CODE_IS_NOT_EXIST(10001, "错误码未知"),
    OKHTTP_ERROR(10002,"okhttp请求失败");

    private int code;
    private String msg;


    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static ErrorCode codeOf(int code) {
        for (ErrorCode state : values()) {
            if (state.getCode() == code) {
                return state;
            }
        }
        return null;
    }
}
