package com.csvalue.common;

public class ResponseResult<T> {
    private int code;
    private String msg;
    private T data;

    public static <T> ResponseResult<T> success(){
        return new ResponseResult<T>();
    }

    public static <T> ResponseResult<T> success(T result){
        ResponseResult<T> responseResult=new ResponseResult<T>();
        responseResult.setData(result);
        return responseResult;
    }

    public static <T> ResponseResult<T> error(ResponseCode code){
        return new ResponseResult<T>(code.code,code.msg);
    }

    public static <T> ResponseResult<T> error(int code,String msg){
        return new ResponseResult<T>(code,msg);
    }

    public ResponseResult(){
        this(ResponseCode.OK.code,ResponseCode.OK.msg);
    }

    public ResponseResult(ResponseCode responseCode){
        this(responseCode.code,responseCode.msg);
    }

    public ResponseResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
