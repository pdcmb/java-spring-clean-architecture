package com.pdcmb.covid19stats.configuration;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;

@Component
public class Region{

    private String code;
    private String name;
    private List<String> countries;


    public Region() {
    }

    public Region(String code, String name, List<String> countries) {
        this.code = code;
        this.name = name;
        this.countries = countries;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
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

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Region)) {
            return false;
        }
        Region region = (Region) o;
        return Objects.equals(code, region.code) && Objects.equals(name, region.name) && Objects.equals(countries, region.countries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name, countries);
    }

    @Override
    public String toString() {
        return "{" +
            " code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", countries='" + getCountries() + "'" +
            "}";
    }
    
}

   

