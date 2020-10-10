package com.pdcmb.covid19stats.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Domain entity that rappresents a region. Region is made of more countries.
 * 
 * @author Mateusz Ziomek
 */
public class Region {

    /**
     * Name of the region
     */
    private String name;

    /**
     * Countries in that region
     */
    private List<Country> countries = new ArrayList<>();


    /**
     * Default empty constructor
     */
    public Region() {}


    /**
     * Consructor which sets all fields
     * 
     * @param name
     * @param countries
     */
    public Region(String name, List<Country> countries) {
        this.name = name;
        this.countries = countries;
    }

    /**
     * Returns region's name
     * 
     * @return <b>String</b> name of the region
     */
    public String getName() {
        return this.name;
    }

	public void setName(String name) {
        this.name = name;
    }


    /**
     * Returns countries in that region
     * 
     * @return <b>List</b> list of countries in the region
     */
    public List<Country> getCountries() {
        return this.countries;
    }

    /**
     * Setter which sets list of countries 
     * 
     * @param countries List of countries in the region
     */
    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    /**
     * Fluent setter for setting region's name 
     * 
     * @param name Regions name
     * @return <b>Region</b> Current instance of Region class
     */
    public Region name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Fluent setter which sets list of countries 
     * 
     * @param countries List of countries
     * @return <b>Region</b> Current instance of Region class
     */
    public Region countries(List<Country> countries) {
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
