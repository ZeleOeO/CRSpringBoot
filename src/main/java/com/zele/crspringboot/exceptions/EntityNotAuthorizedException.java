package com.zele.crspringboot.exceptions;

public class EntityNotAuthorizedException extends RuntimeException {
    public EntityNotAuthorizedException(String message) {
        super(message);
    }
}
