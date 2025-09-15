package com.project.recruitment.exceptions;

public class PermissionDenyException extends Exception{
    public PermissionDenyException(String message){
        super(message);
    }
}
