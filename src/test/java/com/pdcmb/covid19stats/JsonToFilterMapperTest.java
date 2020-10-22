package com.pdcmb.covid19stats;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.pdcmb.covid19stats.domain.entities.Filter;
import com.pdcmb.covid19stats.domain.entities.Filter.Operator;
import com.pdcmb.covid19stats.presentation.models.mappers.JsonToFilter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class JsonToFilterMapperTest {
    
    @Autowired
    private JsonToFilter filterMapper;

    @Test
    @DisplayName("Filter mapper test")
    void testInteger() {
        
        String json = "{\"active\":{\"$eq\":200}}";
        Filter correctFilter = new Filter(Operator.EQUAL, "active", 200);

        Filter filter = filterMapper.map(json)[0];
        assertEquals(correctFilter, filter);
    }
}
