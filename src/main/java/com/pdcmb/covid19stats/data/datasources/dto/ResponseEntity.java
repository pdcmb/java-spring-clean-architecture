package com.pdcmb.covid19stats.data.datasources.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.Objects;
/**
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseEntity {
    
    private String country;
    private String countryCode;
    private int confirmed;
    private int deaths;
    private int recovered;
    private int active;
    private Instant date;

    public ResponseEntity(){}

    public ResponseEntity(String country, String countryCode, int confirmed, int deaths, int recovered, int active, Instant date) {
        this.country = country;
        this.countryCode = countryCode;
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
        this.active = active;
        this.date = date;
    }

    @JsonProperty("Country")
    public String getCountry() {
        return this.country;
    }

    @JsonProperty("Country")
    public void setCountry(String country) {
        this.country = country;
    }

    @JsonProperty("CountryCode")
    public String getCountryCode() {
        return this.countryCode;
    }

    @JsonProperty("CountryCode")
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @JsonProperty("Confirmed")
    public int getConfirmed() {
        return this.confirmed;
    }

    @JsonProperty("Confirmed")
    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    @JsonProperty("Deaths")
    public int getDeaths() {
        return this.deaths;
    }

    @JsonProperty("Deaths")
    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    @JsonProperty("Recovered")
    public int getRecovered() {
        return this.recovered;
    }

    @JsonProperty("Recovered")
    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    @JsonProperty("Active")
    public int getActive() {
        return this.active;
    }

    @JsonProperty("Active")
    public void setActive(int active) {
        this.active = active;
    }

    @JsonProperty("Date")
    public Instant getDate() {
        return this.date;
    }

    @JsonProperty("Date")
    public void setDate(Instant date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ResponseEntity)) {
            return false;
        }
        ResponseEntity responseEntity = (ResponseEntity) o;
        return Objects.equals(country, responseEntity.country) && Objects.equals(countryCode, responseEntity.countryCode) && confirmed == responseEntity.confirmed && deaths == responseEntity.deaths && recovered == responseEntity.recovered && active == responseEntity.active && Objects.equals(date, responseEntity.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, countryCode, confirmed, deaths, recovered, active, date);
    }

    @Override
    public String toString() {
        return "{" +
            " country='" + getCountry() + "'" +
            ", countryCode='" + getCountryCode() + "'" +
            ", confirmed='" + getConfirmed() + "'" +
            ", deaths='" + getDeaths() + "'" +
            ", recovered='" + getRecovered() + "'" +
            ", active='" + getActive() + "'" +
            ", date='" + getDate() + "'" +
            "}";
    }

}
