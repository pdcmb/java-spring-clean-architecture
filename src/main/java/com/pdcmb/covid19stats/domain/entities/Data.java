package com.pdcmb.covid19stats.domain.entities;

import java.time.Instant;
import java.util.Objects;

/**
 * Domain value that holds data 
 * 
 * @author Mateusz Ziomek
 */
public class Data {


    private Region region;
    /**
     * Date rappresented by {@link Instant} instance. ISO 8601 compatible 
     */
    private Instant date;

    /**
     * Confirmed cases in that day
     */
    private Integer confirmed;

    /**
     * Number of deaths
     */
    private Integer deaths;

    /**
     * Number of recovered
     */
    private Integer recovered;

    /**
     * Number of active cases
     */
    private Integer active;
    

    /**
     * Default empty constructor
     */
    public Data() {}

    /**
     * Constructor which sets all fields
     * 
     * @param region
     * @param date
     * @param confirmed
     * @param deaths
     * @param recovered
     * @param active
     */
    public Data(Region region, Instant date, Integer confirmed,
                 Integer deaths, Integer recovered, Integer active) {
        this.region = region;
        this.date = date;
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
        this.active = active;
    }

    /**
     * Returns region
     * 
     * @return {@link Region} instance ti which this data belongs
     */
    public Region getRegion(){
        return this.region;
    }

    /**
     * Sets region.
     * It handles the many-to-one relationship by removing this {@link Data}
     * instance from the current region (if not null) and then sets the new region
     * 
     * @param region {@link Region} instance to set.
     */
    public void setRegion(Region region){
        if (this.region != null){
            this.region.removeData(this);
        }
        this.region = region;
        this.region.addData(this);
      }

    /**
     * Returns date
     * 
     * @return Date rappresented by {@link Instant}
     */
    public Instant getDate() {
        return this.date;
    }

    /**
     * Sets date for this data record
     * 
     * @param date Date as a {@link Instant} instance
     */
    public void setDate(Instant date) {
        this.date = date;
    }

    /**
     * Returns confirmed cases
     * 
     * @return Number of confirmed cases
     */
    public Integer getConfirmed() {
        return this.confirmed;
    }

    /**
     * Sets number of confimerd cases
     * 
     * @param confirmed Confirmed cases
     */
    public void setConfirmed(Integer confirmed) {
        this.confirmed = confirmed;
    }

    /**
     * Return number of deaths
     * 
     * @return Number of deaths
     */
    public Integer getDeaths() {
        return this.deaths;
    }

    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }

    /**
     * Gets number of recovered
     * 
     * @return Number of recovered
     */
    public Integer getRecovered() {
        return this.recovered;
    }

    /**
     * Sets number of recovered
     * 
     * @param recovered Number of recovered
     */
    public void setRecovered(Integer recovered) {
        this.recovered = recovered;
    }

    /**
     * Returns active cases
     * 
     * @return Number of active cases
     */
    public Integer getActive() {
        return this.active;
    }

    /**
     * Sets active cases
     * 
     * @param active Number of active cases
     */
    public void setActive(Integer active) {
        this.active = active;
    }

    /**
     * Fluent setter which sets region
     * 
     * @param region {@link Region} to set 
     * @return Current {@link Data} instance 
     */
    public Data region(Region region) {
        this.setRegion(region);
        return this;
    }

    /**
     * Fluent setter which sets date
     * 
     * @param date 
     * @return Current {@link Data} instance 
     */
    public Data date(Instant date) {
        this.date = date;
        return this;
    }

    /**
     * Fluent setter for setting confirmed cases
     * 
     * @param confirmed Number of confirmed cases
     * @return Current {@link Data} instance 
     */
    public Data confirmed(Integer confirmed) {
        this.confirmed = confirmed;
        return this;
    }

    /**
     * Fluent setter for setting number of deaths
     * 
     * @param deaths Number of deaths
     * @return This {@link Data} instance 
     */
    public Data deaths(Integer deaths) {
        this.deaths = deaths;
        return this;
    }

    public Data recovered(Integer recovered) {
        this.recovered = recovered;
        return this;
    }

    /**
     * Fluent setter, sets active cases 
     * 
     * @param active Active cases
     * @return This {@link Data} instance
     */
    public Data active(Integer active) {
        this.active = active;
        return this;
    }

    /**
     * Merges this {@link Data} instance with the one provided as argument.
     * All Integer fields (confirmed, deaths, recovered, active) of the data passed
     * as a parameter will be added to this instance fields.
     * 
     * @param data {@link Data} to be added to this instance
     * @return Current instance
     */
    public Data merge(Data data){
        this.confirmed = this.confirmed + data.confirmed;
        this.deaths = this.deaths + data.deaths;
        this.recovered = this.recovered + data.recovered;
        this.active = this.active + data.active;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Data)) {
            return false;
        }
        Data day = (Data) o;
        return confirmed == day.confirmed && deaths == day.deaths && recovered == day.recovered && active == day.active;
    }

    @Override
    public int hashCode() {
        return Objects.hash(confirmed, deaths, recovered, active);
    }

    @Override
    public String toString() {
        return "{" + date.toString() + "'" +
            " confirmed='" + getConfirmed() + "'" +
            ", deaths='" + getDeaths() + "'" +
            ", recovered='" + getRecovered() + "'" +
            ", active='" + getActive() + "'" +
            "}";
    }

}
