package com.pdcmb.covid19stats;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.pdcmb.covid19stats.configuration.AppProperties;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestYamlConfiguration {


    @Autowired
    private AppProperties appProperties;
 

    @Test
    public void readConfigurationTest() {

        assertEquals(2, appProperties.getRegions().size());

        assertEquals("middle-east", appProperties.getRegions().get(0).getCode());

        assertEquals("Middle East", appProperties.getRegions().get(0).getName());

        assertEquals("eu", appProperties.getRegions().get(1).getCode());

        assertEquals("European Union", appProperties.getRegions().get(1).getName());
 
    }
}
