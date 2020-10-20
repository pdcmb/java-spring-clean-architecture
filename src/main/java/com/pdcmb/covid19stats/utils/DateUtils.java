package com.pdcmb.covid19stats.utils;

import java.time.DateTimeException;
import java.time.Instant;

public class DateUtils {

    public static Boolean isValidDate(String date){
        try {
            Instant.parse(date);
        } catch (DateTimeException e) {
            return false;
        }
        return true;
    }
    
}
