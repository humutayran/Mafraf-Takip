package com.umut.Masraf_Takip.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    private static final String NOT_FOUND = " not found";
    private static final String WITH_NAME = " with name: ";
    private String objectType;

    public NotFoundException(Class<?> clazz) {
        super(clazz.getSimpleName() + NOT_FOUND);
        objectType = clazz.getSimpleName();
    }
}
