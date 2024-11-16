package com.example.demo.exception;

import java.io.Serial;

public class TodoException extends Exception{
    private static final long serialVersionUID =1L;

    public TodoException(String message){
        super(message);
    }

    public static String NotFoundException(String id){
        return "Todo with" + id + "not found";
    }

    public static String TodoAlreadyExists(){
        return "Todo with given name already exist";
    }


}
