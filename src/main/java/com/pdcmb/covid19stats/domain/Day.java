package com.pdcmb.covid19stats.domain;

import java.time.Instant;
import java.util.Objects;

/**
 * Domain entity that contains confirmed cases, deaths, recovered and active
 * cases in given day, all data are cumulative.
 * 
 * @author Mateusz Ziomek
 */
public class Day {

    private Instant date;
    private int confirmed;
    private int deaths;
    private int recovered;
    private int active;
    

    public Day() {}

    public Day(Instant date, int confirmed, int deaths, int recovered, int active) {
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
    public Day date(Instant date) {
        this.date = date;
        return this;
    }

    public Day confirmed(int confirmed) {
        this.confirmed = confirmed;
        return this;
    }

    public Day deaths(int deaths) {
        this.deaths = deaths;
        return this;
    }

    public Day recovered(int recovered) {
        this.recovered = recovered;
        return this;
    }

    public Day active(int active) {
        this.active = active;
        return this;
    }



    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Day)) {
            return false;
        }
        Day day = (Day) o;
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
