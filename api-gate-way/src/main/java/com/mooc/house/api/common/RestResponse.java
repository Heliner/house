package com.mooc.house.api.common;

public class RestResponse<T> {
    private int code;
    private String msg;
    private T result;

    public static <T> RestResponse<T> success(){
        RestResponse<T> restResponse=new RestResponse<>();
        return restResponse;
    }
    public static <T> RestResponse<T> success(T result){
        RestResponse<T> restResponse=new RestResponse<>();
        restResponse.setResult(result);
        return restResponse;
    }
    public static <T> RestResponse<T> fail(){
        RestResponse<T> restResponse=new RestResponse<>(RestCode.UNKOWN_ERROR.code,RestCode.UNKOWN_ERROR.msg);
        return restResponse;
    }

    public RestResponse() {
        this.code=RestCode.OK.code;
        this.msg=RestCode.OK.msg;
    }

    public RestResponse(int code, String msg) {
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

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
