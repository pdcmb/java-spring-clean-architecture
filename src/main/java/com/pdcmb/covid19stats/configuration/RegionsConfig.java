package com.pdcmb.covid19stats.configuration;

import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    
        public Region name(String name) {
            this.name = name;
            return this;
        }
    
        public Region countries(List<String> countries) {
            this.countries = countries;
            return this;
        }
    
        @Override
        public boolean equals(Object o) {
            if (o == this)
                return true;
            if (!(o instanceof Region)) {
                return false;
            }
            Region region = (Region) o;
            return Objects.equals(name, region.name) && Objects.equals(countries, region.countries);
        }
    
        @Override
        public int hashCode() {
            return Objects.hash(name, countries);
        }
    
        @Override
        public String toString() {
            return "{" +
                " name='" + getName() + "'" +
                ", countries='" + getCountries() + "'" +
                "}";
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

    public RegionsConfig regions(Map<String,Region> regions) {
        this.regions = regions;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof RegionsConfig)) {
            return false;
        }
        RegionsConfig regionsConfig = (RegionsConfig) o;
        return Objects.equals(regions, regionsConfig.regions);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(regions);
    }

    @Override
    public String toString() {
        return "{" +
            " regions='" + getRegions() + "'" +
            "}";
    }
   
}

