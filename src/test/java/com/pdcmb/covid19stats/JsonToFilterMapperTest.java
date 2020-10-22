package com.pdcmb.covid19stats;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import com.pdcmb.covid19stats.domain.entities.Filter;
import com.pdcmb.covid19stats.domain.entities.Filter.Operator;
import com.pdcmb.covid19stats.presentation.models.mappers.JsonToFilter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class JsonToFilterMapperTest {
    
    List<String> jsonString = Arrays.asList(
        "{\"active\":{\"$eq\":200}}",
        "{\"confirmed\":{\"$bt\":[1000,2000]}}",
        "{\"date\":{\"$eq\":\"2020-10-10T00:00:00Z\"}}",
        "{\"date\":{\"$bt\":\"2020-10-10T00:00:00Z\",\"2020-10-20T00:00:00Z\"}}"
    );

    List<Filter> filters = Arrays.asList(
        new Filter(Operator.EQUAL, "active", 200),
        new Filter(Operator.BETWEEN, "confirmed", new Integer[]{1000, 2000}),
        new Filter(Operator.EQUAL, "date", Instant.parse("2020-10-10T00:00:00Z")),
        new Filter(Operator.BETWEEN, "date",
                new Instant[]{Instant.parse("2020-10-10T00:00:00Z"),Instant.parse("2020-10-20T00:00:00Z")})
    );


    @Autowired
    private JsonToFilter mapper;

    @Test
    @DisplayName("Filter mapper test")
    void testInteger() {
        
        for(int i = 0 ; i < jsonString.size(); i++){
            Filter[] filter = mapper.map(jsonString.get(i));
            assertNotNull(filter);
            assertEquals(filters.get(i), filter);
        }


    }
}
