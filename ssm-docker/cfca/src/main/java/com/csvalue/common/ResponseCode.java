package com.csvalue.common;

public enum ResponseCode {

    DISCONNECT(1111,"未连接"),

    OK(200,"成功"),

    FAIL(500,"系统异常"),

    ;

    public final int code;
    public final String msg;

    private ResponseCode(int code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
