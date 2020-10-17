package com.pdcmb.covid19stats.presentation.models;

import java.time.Instant;
import java.util.Objects;

public class DataResponseModel{

    private Instant date;
    private int confirmed;
    private int deaths;
    private int recovered;
    private int active;

    public DataResponseModel() {}

    public DataResponseModel(Instant date, int confirmed, int deaths, int recovered, int active) {
        this.date = date;
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
        this.active = active;
    }

    public Instant getDate() {
        return this.date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public int getConfirmed() {
        return this.confirmed;
    }

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

    public DataResponseModel date(Instant date) {
        this.date = date;
        return this;
    }

    public DataResponseModel confirmed(int confirmed) {
        this.confirmed = confirmed;
        return this;
    }

    public DataResponseModel deaths(int deaths) {
        this.deaths = deaths;
        return this;
    }

    public DataResponseModel recovered(int recovered) {
        this.recovered = recovered;
        return this;
    }

    public DataResponseModel active(int active) {
        this.active = active;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof DataResponseModel)) {
            return false;
        }
        DataResponseModel dataResponseModel = (DataResponseModel) o;
        return Objects.equals(date, dataResponseModel.date) && confirmed == dataResponseModel.confirmed && deaths == dataResponseModel.deaths && recovered == dataResponseModel.recovered && active == dataResponseModel.active;
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, confirmed, deaths, recovered, active);
    }

    @Override
    public String toString() {
        return "{" +
            " date='" + getDate() + "'" +
            ", confirmed='" + getConfirmed() + "'" +
            ", deaths='" + getDeaths() + "'" +
            ", recovered='" + getRecovered() + "'" +
            ", active='" + getActive() + "'" +
            "}";
    }





    
}
