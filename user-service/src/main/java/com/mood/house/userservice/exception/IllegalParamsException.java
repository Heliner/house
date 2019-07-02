package com.mood.house.userservice.exception;

public class IllegalParamsException extends RuntimeException implements WithTypeException {

    private final Type type;

    public IllegalParamsException(Type type, String msg) {
        super(msg);
        this.type = type;
    }

    public enum Type {
        WRONG_PAGE_NUM, WRONG_TYPE;
    }
}
