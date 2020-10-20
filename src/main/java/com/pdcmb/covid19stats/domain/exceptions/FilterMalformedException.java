package com.pdcmb.covid19stats.domain.exceptions;

public class FilterMalformedException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 8243768206807680631L;

    public FilterMalformedException(){

    }
    public FilterMalformedException(String message){
        super(message);
    }
    
}
