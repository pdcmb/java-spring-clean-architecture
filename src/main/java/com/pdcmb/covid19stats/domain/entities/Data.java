package com.pdcmb.covid19stats.domain.entities;

import java.time.Instant;
import java.util.Objects;

/**
 * Domain value object that holds data 
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
    private int confirmed;

    /**
     * 
     */
    private int deaths;

    /**
     * 
     */
    private int recovered;

    /**
     * 
     */
    private int active;
    

    public Data() {}

    public Data(Instant date, int confirmed, int deaths, int recovered, int active) {
        this.date = date;
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
        this.active = active;
    }

    /**
     * 
     * 
     * @return
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
    public int getConfirmed() {
        return this.confirmed;
    }

    /**
     * 
     * @param confirmed
     */
    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    public int getDeaths() {
        return this.deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getRecovered() {
        return this.recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    public int getActive() {
        return this.active;
    }

    public void setActive(int active) {
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

    public Data confirmed(int confirmed) {
        this.confirmed = confirmed;
        return this;
    }

    public Data deaths(int deaths) {
        this.deaths = deaths;
        return this;
    }

    public Data recovered(int recovered) {
        this.recovered = recovered;
        return this;
    }

    public Data active(int active) {
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
