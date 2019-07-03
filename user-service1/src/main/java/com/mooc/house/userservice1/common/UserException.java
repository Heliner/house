package com.mooc.house.userservice1.common;

import com.mooc.house.userservice1.exception.WithTypeException;

public class UserException extends RuntimeException implements WithTypeException {

    public UserException(Type type, String message) {
        super(message);
        this.type = type;
    }

    private Type type;

    public enum Type {
        USER_NOT_FIND, USER_AUTH_FAIL, USER_WEAK_UP_FAIL,USER_NOT_LOGIN  ;
    }
    public Type type(){
        return type;
    }
}
