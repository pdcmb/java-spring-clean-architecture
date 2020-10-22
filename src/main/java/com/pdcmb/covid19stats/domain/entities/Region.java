package com.pdcmb.covid19stats.domain.entities;

import java.util.List;
import java.util.Objects;

/**
 * Domain entity that rappresents a region dataset.
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
     * Data relevant this region
     */
    private List<Data> data;


    /**
     * Default empty constructor
     */
    public Region() {}


    /**
     * Consructor which sets all fields
     * 
     * @param regionCode String rappresenting region code
     * @param name Region name
     * @param data Data for this region
     */
    public Region(String regionCode, String name, List<Data> data) {
        this.regionCode = regionCode;
        this.name = name;
        this.data = data;
    }

    /**
     * Returns region code
     * 
     * @return Region code
     */
    public String getRegionCode(){
        return this.regionCode;
    }

    /**
     * Sets region code
     * 
     * @param regionCode String containg region code
     */
    public void setRegionCode(String regionCode){
        this.regionCode = regionCode;
    }
    /**
     * Returns region's name
     * 
     * @return String containing name of the region
     */
    public String getName() {
        return this.name;
    }

    /**
     * 
     */
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
     * Setter which sets data for this region
     * 
     * @param data List containg {@link Data} instances to be added 
     */
    public void setData(List<Data> data) {
        this.data = data;
    }

    /**
     * Adds single {@link Data} to this dataset
     * 
     * @param data Data to be added
     */
    public void addData(Data data) {
        if(!data.getRegion().equals(this))
            data.setRegion(this);
        this.data.add(data);
    }
    
    /**
     * Removes single {@link Data} instance
     * 
     * @param data Data object to be removed from this region
     */
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
     * @return Current instance of {@link Region} class
     */
    public Region name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Fluent setter which sets List of {@link Data} 
     * 
     * @param data List of odata
     * @return Current instance of {@link Region} class
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
