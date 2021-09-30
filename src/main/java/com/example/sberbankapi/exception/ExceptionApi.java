package com.example.sberbankapi.exception;

public class ExceptionApi extends Exception {

    public ExceptionApi(Exception e){
      super(e.getMessage());
    }

    public String getMessage(){
        return "";
    }
}
