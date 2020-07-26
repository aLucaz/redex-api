package com.application.shared.exception.custom;

public class RouteNotFoundException extends RuntimeException {
    public RouteNotFoundException(String message){
        super(message);
    }
}
