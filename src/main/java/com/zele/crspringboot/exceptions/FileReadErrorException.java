package com.zele.crspringboot.exceptions;

public class FileReadErrorException extends RuntimeException {
    public FileReadErrorException(String message) {
        super(message);
    }
}
