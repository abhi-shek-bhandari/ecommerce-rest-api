package com.masai.demo.exception;

public class PromocodeException extends RuntimeException{
    public PromocodeException() {
    }

    public PromocodeException(String message) {
        super(message);
    }
}
