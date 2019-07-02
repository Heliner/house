package com.mooc.house.userservice1.common;

public enum RestCode {
    OK(0, "OK"),
    UNKOWN_ERROR(1, "服务异常"),
    WRONG_PAGE(10100, "页码不存在");

    public final int code;
    public final String msg;

    private RestCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
