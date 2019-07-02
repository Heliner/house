package com.mood.house.userservice.common;

import com.google.common.collect.ImmutableMap;
import com.mood.house.userservice.exception.IllegalParamsException;
import com.mood.house.userservice.exception.WithTypeException;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang.reflect.FieldUtils;

public class Exception2CodeRepo {
    private static final ImmutableMap<Object, RestCode> MAP = ImmutableMap.<Object, RestCode>builder()
            .put(IllegalParamsException.Type.WRONG_PAGE_NUM, RestCode.WRONG_PAGE)
            .put(IllegalStateException.class, RestCode.UNKOWN_ERROR).build();


    public static RestCode getCode(Throwable throwable) {
        if (throwable == null)
            return RestCode.UNKOWN_ERROR;
        Object target = throwable;
        if (target instanceof WithTypeException) {
            Object type = getType(throwable);
            if (null != type)
                target = type;
        }
        if (MAP.containsKey(target))
            return MAP.get(target);
        Throwable rootCause = ExceptionUtils.getRootCause(throwable);
        if (null != rootCause)
            return getCode(rootCause);

        return RestCode.UNKOWN_ERROR;
    }

    private static Object getType(Throwable throwable) {
        try {
            return FieldUtils.readDeclaredField(throwable, "type", true);
        } catch (Exception e) {
            return null;
        }
    }
}
