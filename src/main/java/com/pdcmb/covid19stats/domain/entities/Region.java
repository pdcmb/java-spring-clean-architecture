package com.pdcmb.covid19stats.domain.entities;

import java.util.List;
import java.util.Objects;

/**
 * Domain entity that rappresents a region. Region is made of more countries.
 * 
 * @author Mateusz Ziomek
 */
public class Region {

    /**
     * Region code that unambiguously identifies this region
     */
    private String regionCode;

    /**
     * Name of the region
     */
    private String name;

    /**
     * Data for this region
     */
    private List<Data> data;


    /**
     * Default empty constructor
     */
    public Region() {}


    /**
     * Consructor which sets all fields
     * 
     * @param regionCode
     * @param name
     * @param data
     */
    public Region(String regionCode, String name, List<Data> data) {
        this.regionCode = regionCode;
        this.name = name;
        this.data = data;
    }

    /**
     * Returns region code
     * 
     * @return {@link String} region code
     */
    public String getRegionCode(){
        return this.regionCode;
    }

    /**
     * 
     * @param regionCode
     */
    public void setRegionCode(String regionCode){
        this.regionCode = regionCode;
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
    public List<Data> getData() {
        return this.data;
    }

    /**
     * Setter which sets list of countries 
     * 
     * @param countries List of countries in the region
     */
    public void setData(List<Data> countries) {
        this.data = countries;
    }

    /**
     * Adds {@lin}
     * 
     * @param data
     */
    public void addData(Data data) {
        if(!data.getRegion().equals(this))
            data.setRegion(this);
        this.data.add(data);
    }
    
    public void removeData(Data data) {
        data.setRegion(null);
        this.data.remove(data);
    }

    public Region regionCode(String regionCode){
        this.regionCode = regionCode;
        return this;
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
    public Region data(List<Data> data) {
        this.data = data;
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
        return Objects.equals(name, region.name) && Objects.equals(data, region.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, data);
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", data='" + getData() + "'" +
            "}";
    }

    
}
