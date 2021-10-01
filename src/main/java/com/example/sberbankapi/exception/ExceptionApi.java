package com.example.sberbankapi.exception;

public class ExceptionApi extends Exception {

    private String reason;
    private int number;

    public ExceptionApi(String reason, int number){
      super(reason);
      this.number=number;
    }

    public String getMessage(){
        return reason;
    }

    public int getNumber() {
        return number;
    }
}
