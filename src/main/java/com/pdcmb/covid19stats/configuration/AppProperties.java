package com.pdcmb.covid19stats.configuration;

import java.util.List;
import java.util.Objects;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
public class AppProperties {
    
    private List<Region> regions;


    public AppProperties() {}

    public AppProperties(List<Region> regions) {
        this.regions = regions;
    }

    public List<Region> getRegions() {
        return this.regions;
    }

    public void setRegions(List<Region> regions) {
        this.regions = regions;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof AppProperties)) {
            return false;
        }
        AppProperties appProperties = (AppProperties) o;
        return Objects.equals(regions, appProperties.regions);
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
