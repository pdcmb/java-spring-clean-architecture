package com.pdcmb.covid19stats;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import com.pdcmb.covid19stats.domain.entities.Data;
import com.pdcmb.covid19stats.domain.entities.Filter;
import com.pdcmb.covid19stats.domain.entities.Filter.Operator;
import com.pdcmb.covid19stats.domain.exceptions.FilterMalformedException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FilterTest{

    List<Data> mockupData = Arrays.asList(
        new Data(Instant.parse("2020-09-25T00:00:00Z"), 1000, 10, 400, 800),
        new Data(Instant.parse("2020-09-20T00:00:00Z"), 500, 5, 200, 600)
        );
    
    // Test data filtering
    @Test
    @DisplayName("Test filter with equal operator")
    void testEqual(){
        Filter filter = new Filter(Operator.EQUAL, "confirmed", 1000);
        assertEquals(true, filter.apply(mockupData.get(0)));

        filter = new Filter(Operator.EQUAL, "active", 500);
        assertEquals(false, filter.apply(mockupData.get(0)));

        filter = new Filter(Operator.EQUAL, "date", Instant.parse("2020-09-25T00:00:00Z"));
        assertEquals(true, filter.apply(mockupData.get(0)));

        filter = new Filter(Operator.EQUAL, "date", Instant.parse("2020-09-23T00:00:00Z"));
        assertEquals(false, filter.apply(mockupData.get(0)));
    }

    @Test
    @DisplayName("Test filter with greater operator")
    void testGreater(){
        Filter filter = new Filter(Operator.GREATER, "confirmed", 800);
        assertEquals(true, filter.apply(mockupData.get(0)));
        assertEquals(false, filter.apply(mockupData.get(1)));

        filter = new Filter(Operator.GREATER, "date", Instant.parse("2020-09-24T00:00:00Z"));
        assertEquals(true, filter.apply(mockupData.get(0)));
        assertEquals(false, filter.apply(mockupData.get(1)));
    }

    @Test
    @DisplayName("Test filter with less operator")
    void testLess(){
        Filter filter = new Filter(Operator.LESS, "confirmed", 800);
        assertEquals(false, filter.apply(mockupData.get(0)));
        assertEquals(true, filter.apply(mockupData.get(1)));

        filter = new Filter(Operator.LESS, "date", Instant.parse("2020-09-24T00:00:00Z"));
        assertEquals(false, filter.apply(mockupData.get(0)));
        assertEquals(true, filter.apply(mockupData.get(1)));
    }


    @Test
    @DisplayName("Testing if exception are thrown correctly")
    void testExceptions(){
        assertThrows(FilterMalformedException.class, () -> { new Filter(Operator.GREATER, "field", 800); } );
        assertThrows(FilterMalformedException.class, () -> { new Filter(Operator.GREATER, "field", 800); } );
        assertThrows(FilterMalformedException.class, () -> { new Filter(Operator.BETWEEN, "field", 800); } );
    }



}
