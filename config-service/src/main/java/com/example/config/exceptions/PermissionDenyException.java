package com.example.config.exceptions;

public class PermissionDenyException extends Exception{
    public PermissionDenyException(String message){
        super(message);
    }
}
