package com.pdcmb.covid19stats.presentation.models;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Class that rappresents a 
 * 
 * @author Mateusz Ziomek
 */
public class DataResponseModel{


    /**
     * 
     * @author Mateusz Ziomek
     */
    public static class Metdata{
        public final static List<FieldResponseModel> METADATA = Arrays.asList(
            new FieldResponseModel("regionCode", "Region code used to identify a region", "String"),
            new FieldResponseModel("regionName", "Region name", "String"),
            new FieldResponseModel("date", "Day to which data reff", "String"),
            new FieldResponseModel("confirmed", "Confirmed", "Integer"),
            new FieldResponseModel("deaths", "Deaths", "Integer"),
            new FieldResponseModel("active", "Active", "Integer")
        );
    }

    private String regionCode;
    private String regionName;
    private Instant date;
    private Integer confirmed;
    private Integer deaths;
    private Integer recovered;
    private Integer active;

    public DataResponseModel() {}


    public DataResponseModel(String regionCode, String regionName, Instant date,
                                Integer confirmed, Integer deaths, Integer recovered,
                                Integer active) {
        this.regionCode = regionCode;
        this.regionName = regionName;
        this.date = date;
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
        this.active = active;
    }

    public String getRegionCode() {
        return this.regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getRegionName() {
        return this.regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Instant getDate() {
        return this.date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Integer getConfirmed() {
        return this.confirmed;
    }

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

    public DataResponseModel regionName(String regionName) {
        this.regionName = regionName;
        return this;
    }

    public DataResponseModel regionCode(String regionCode) {
        this.regionCode = regionCode;
        return this;
    }

    public DataResponseModel date(Instant date) {
        this.date = date;
        return this;
    }

    public DataResponseModel confirmed(Integer confirmed) {
        this.confirmed = confirmed;
        return this;
    }

    public DataResponseModel deaths(Integer deaths) {
        this.deaths = deaths;
        return this;
    }

    public DataResponseModel recovered(Integer recovered) {
        this.recovered = recovered;
        return this;
    }

    public DataResponseModel active(Integer active) {
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
        return Objects.equals(regionName, dataResponseModel.regionName) && Objects.equals(regionCode, dataResponseModel.regionCode) && Objects.equals(date, dataResponseModel.date) && Objects.equals(confirmed, dataResponseModel.confirmed) && Objects.equals(deaths, dataResponseModel.deaths) && Objects.equals(recovered, dataResponseModel.recovered) && Objects.equals(active, dataResponseModel.active);
    }

    @Override
    public int hashCode() {
        return Objects.hash(regionName, regionCode, date, confirmed, deaths, recovered, active);
    }

    @Override
    public String toString() {
        return "{" +
            " regionName='" + getRegionName() + "'" +
            ", regionCode='" + getRegionCode() + "'" +
            ", date='" + getDate() + "'" +
            ", confirmed='" + getConfirmed() + "'" +
            ", deaths='" + getDeaths() + "'" +
            ", recovered='" + getRecovered() + "'" +
            ", active='" + getActive() + "'" +
            "}";
    }
    
}
