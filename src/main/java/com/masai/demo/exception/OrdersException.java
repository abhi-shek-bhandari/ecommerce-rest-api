package com.masai.demo.exception;

public class OrdersException extends RuntimeException{
    public OrdersException() {
    }

    public OrdersException(String message) {
        super(message);
    }
}
