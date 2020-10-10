package com.pdcmb.covid19stats.domain;

import java.util.List;
import java.util.Objects;

/**
 * Domain entity model that rappresents data for a 
 * single country
 * 
 * @author Mateusz Ziomek
 */
public class Country {

    private String countryCode;
    private String name;
    private List<Day> days;

    /**
     * Default empty constructor
     */
    public Country(){ }

    /** 
     * Constructor which sets all fields
     * 
     * @param String Country code
     * @param String Country name
     * @param List List of data for each day
     */
    public Country(String countryCode, String name, List<Day> days) {
        this.countryCode = countryCode;
        this.name = name;
        this.days = days;
    }

    /**
     * Returns country code (en, it etc.)
     * 
     * @return <b>String</b> Country code
     */
    public String getCountryCode() {
        return this.countryCode;
    }

    /**
     * Sets country code
     * 
     * @param code Country code
     */
    public void setCountryCode(String code){
        this.countryCode = code;
    }

    /**
     * Returns country name
     * 
     * @return <b>String</b> Country name
     */
    public String getName() {
        return this.name;
    }


    /**
     * Sets country name
     * 
     * @param name Country name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Returns data for every day since first case
     * 
     * @return <b>Collection</b> List of data for every day
     */
    public List<Day> getDays() {
        return this.days;
    }

    /**
     * Sets list containing all relevant data 
     * 
     * @param List List containing data for each day
     */
    public void setDays(List<Day> days) {
        this.days = days;
    }

    /**
     * Fluent setter which sets country code
     * 
     * @param countryCode String containg country code
     * @return <b>Country</b> Current instance of Country class
     */
    public Country countryCode(String countryCode) {
        this.countryCode = countryCode;
        return this;
    }

    /**
     * Fluent setter which sets country name
     * 
     * @param name Country name
     * @return <b>Country</b> Current instance of Country class
     */
    public Country name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Fluent setter which sets List of days
     * 
     * @param days
     * @return <b>Country</b> Current instance of Country class
     */
    public Country days(List<Day> days) {
        this.days = days;
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Country)) {
            return false;
        }
        Country country = (Country) o;
        return Objects.equals(countryCode, country.countryCode) && Objects.equals(name, country.name) && Objects.equals(days, country.days);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryCode, name, days);
    }

    @Override
    public String toString() {
        return "{" +
            " countryCode='" + getCountryCode() + "'" +
            ", name='" + getName() + "'" +
            ", days='" + getDays() + "'" +
            "}";
    }

 
    
}
