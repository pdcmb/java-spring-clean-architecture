package com.pdcmb.covid19stats.configuration;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties
public class RegionsConfig {

    public static class Region{

        private String name;
        private List<String> countries;
    
    
        public Region() {}
    
        public Region(String name, List<String> countries) {
            this.name = name;
            this.countries = countries;
        }
    
        public String getName() {
            return this.name;
        }
    
        public void setName(String name) {
            this.name = name;
        }
    
        public List<String> getCountries() {
            return this.countries;
        }
    
        public void setCountries(List<String> countries) {
            this.countries = countries;
        }
    
        
    }

    private Map<String, Region> regions;

    public RegionsConfig() {}

    public RegionsConfig(Map<String,Region> regions) {
        this.regions = regions;
    }

    public Map<String,Region> getRegions() {
        return this.regions;
    }

    public void setRegions(Map<String,Region> regions) {
        this.regions = regions;
    }
   
}

