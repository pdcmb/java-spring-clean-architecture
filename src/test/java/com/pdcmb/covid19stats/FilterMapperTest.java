package com.pdcmb.covid19stats;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.pdcmb.covid19stats.domain.entities.Filter;
import com.pdcmb.covid19stats.domain.entities.Filter.Operator;
import com.pdcmb.covid19stats.presentation.models.mappers.FilterMapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class FilterMapperTest {
    
    @Autowired
    private FilterMapper filterMapper;

    @Test
    @DisplayName("Filter mapper test")
    void testInteger() {
        
        String json = "{\"active\":{\"$eq\":200}}";
        Filter correctFilter = new Filter(Operator.EQUAL, "active", 200);

        Filter filter = filterMapper.transform(json).get(0);
        assertEquals(correctFilter, filter);
    }
}
