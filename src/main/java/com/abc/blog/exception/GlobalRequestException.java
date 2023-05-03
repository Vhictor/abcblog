package com.abc.blog.exception;

public class GlobalRequestException extends RuntimeException{

    public GlobalRequestException(String message) {
        super(message);
    }
}
