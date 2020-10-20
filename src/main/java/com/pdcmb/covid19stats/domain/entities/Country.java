package com.pdcmb.covid19stats.domain.entities;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Domain entity model that rappresents a country.
 * It holds data set for this particular country 
 * 
 * @author Mateusz Ziomek
 */
public class Country {

    /**
     * Country code
     */
    private String countryCode;

    /**
     * Country name
     */
    private String name;

    /**
     * Dataset for this country
     */
    private ArrayList<Data> data;

    /**
     * Default empty constructor
     */
    public Country(){ }

    /** 
     * Constructor which sets all fields
     * 
     * @param String Country code
     * @param String Country name
     * @param List List containing data for each day
     */
    public Country(String countryCode, String name, ArrayList<Data> data) {
        this.countryCode = countryCode;
        this.name = name;
        this.data = data;
    }

    public void filter(Filter filter){
        this.data = this.data.stream()
                            .filter(filter::apply)
                            .collect(Collectors.toCollection(ArrayList::new));
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
    public ArrayList<Data> getData() {
        return this.data;
    }

    /**
     * Sets list containing all relevant data 
     * 
     * @param List List containing dataset for this country
     */
    public void setData(ArrayList<Data> data) {
        this.data = data;
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
     * Fluent setter which sets Data for this country
     * 
     * @param days
     * @return <b>Country</b> Current instance of Country class
     */
    public Country data(ArrayList<Data> data) {
        this.data = data;
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
        return Objects.equals(countryCode, country.countryCode) && Objects.equals(name, country.name) && Objects.equals(data, country.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryCode, name, data);
    }

    @Override
    public String toString() {
        return "{" +
            " countryCode='" + getCountryCode() + "'" +
            ", name='" + getName() + "'" +
            ", data='" + getData() + "'" +
            "}";
    }



 
    
}
