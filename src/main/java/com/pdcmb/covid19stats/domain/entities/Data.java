package com.pdcmb.covid19stats.domain.entities;

import java.time.Instant;
import java.util.Objects;

/**
 * Domain value that holds data 
 * 
 * @author Mateusz Ziomek
 */
public class Data {


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
    

    public Data() {}

    public Data(Instant date, Integer confirmed, Integer deaths, Integer recovered, Integer active) {
        this.date = date;
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
        this.active = active;
    }

    /**
     * 
     * 
     * @return {@link Instant} date
     */
    public Instant getDate() {
        return this.date;
    }

    /**
     * 
     */
    public void setDate(Instant date) {
        this.date = date;
    }

    /**
     * 
     * @return 
     */
    public Integer getConfirmed() {
        return this.confirmed;
    }

    /**
     * 
     * @param confirmed
     */
    public void setConfirmed(Integer confirmed) {
        this.confirmed = confirmed;
    }

    public Integer getDeaths() {
        return this.deaths;
    }

    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }

    public Integer getRecovered() {
        return this.recovered;
    }

    public void setRecovered(Integer recovered) {
        this.recovered = recovered;
    }

    public Integer getActive() {
        return this.active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    /**
     * Fluent setter which sets date
     * 
     * @param date 
     * @return <b>Day</b> 
     */
    public Data date(Instant date) {
        this.date = date;
        return this;
    }

    public Data confirmed(Integer confirmed) {
        this.confirmed = confirmed;
        return this;
    }

    public Data deaths(Integer deaths) {
        this.deaths = deaths;
        return this;
    }

    public Data recovered(Integer recovered) {
        this.recovered = recovered;
        return this;
    }

    public Data active(Integer active) {
        this.active = active;
        return this;
    }

    public void add(Data data){
        this.confirmed = this.confirmed + data.confirmed;
        this.deaths = this.deaths + data.deaths;
        this.recovered = this.recovered + data.recovered;
        this.active = this.active + data.active;
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
