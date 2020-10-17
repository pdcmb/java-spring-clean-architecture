package com.pdcmb.covid19stats.presentation.models;

import java.time.Instant;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "regionName", "date", "confirmed", "deaths", "recovered", "active" })
public class RegionResponseModel extends DataResponseModel{

    private String regionName;

    private String regionCode;

    public RegionResponseModel() { }

    public RegionResponseModel(String regionName, String regionCode,
                                Instant date, int confirmed, int deaths, int recovered, int active) {
        super(date, confirmed, deaths, recovered, active);
        this.regionName = regionName;
    }

    public RegionResponseModel(String regionName, String regionCode) {
        this.regionName = regionName;
        this.regionCode = regionCode;
    }

    public String getRegionName() {
        return this.regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getRegionCode() {
        return this.regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public RegionResponseModel regionName(String regionName) {
        this.regionName = regionName;
        return this;
    }

    public RegionResponseModel regionCode(String regionCode) {
        this.regionCode = regionCode;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof RegionResponseModel)) {
            return false;
        }
        RegionResponseModel regionResponseModel = (RegionResponseModel) o;
        return Objects.equals(regionName, regionResponseModel.regionName) && Objects.equals(regionCode, regionResponseModel.regionCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(regionName, regionCode);
    }

    @Override
    public String toString() {
        return "{" +
            " regionName='" + getRegionName() + "'" +
            ", regionCode='" + getRegionCode() + "'" +
            "}";
    }

}
