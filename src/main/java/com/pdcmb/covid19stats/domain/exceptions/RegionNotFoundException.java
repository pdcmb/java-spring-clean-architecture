package com.pdcmb.covid19stats.domain.exceptions;

public class RegionNotFoundException extends RuntimeException {


    /**
     *
     */
    private static final long serialVersionUID = -8587165511775872317L;

    public RegionNotFoundException() {
    }

    public RegionNotFoundException(String message){
        super(message);
    }
    
}
