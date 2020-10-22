package com.pdcmb.covid19stats;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import com.pdcmb.covid19stats.data.datasources.Covid19Api;
import com.pdcmb.covid19stats.data.entities.RegionEntity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import reactor.core.scheduler.Schedulers;

@SpringBootTest
public class Covid19ApiDataSourceTest {
    
    @Autowired
    private Covid19Api covid19api; 

    private RegionEntity region;

    @Test
    public void test(){

        assertDoesNotThrow(() -> {
            region = covid19api.getRegions("eu")
                            .publishOn(Schedulers.elastic())
                            .subscribeOn(Schedulers.elastic())
                            .blockFirst();
        });


        assertNotNull(region);
    }
}
