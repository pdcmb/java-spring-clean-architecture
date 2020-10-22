package com.pdcmb.covid19stats.data.entities;

import java.time.Instant;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class DataEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Instant date;
    private Integer confirmed;
    private Integer deaths;
    private Integer recovered;
    private Integer active;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "region_code", nullable = false)
    private RegionEntity region;
    

    public DataEntity() {
    
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
        this.active = active;
        this.region = region;
    }

    public DataEntity(DataEntity dataEntity) {
        this(dataEntity.getId(), dataEntity.getDate(), dataEntity.getConfirmed(), dataEntity.getDeaths(),
             dataEntity.getRecovered(), dataEntity.getActive(), dataEntity.getRegion());
    }

    public DataEntity(Long id, Instant date, Integer confirmed, Integer deaths,
                         Integer recovered, Integer active, RegionEntity region) {
        this.id = id;
        this.date = date;
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
        this.active = active;
        this.region = region;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setConfirmerd(Integer confirmerd) {
        this.confirmed = confirmerd;
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

    public RegionEntity getRegion() {
        return this.region;
    }

    public void setRegion(RegionEntity region) {
        this.region = region;
    }

    public DataEntity id(Long id) {
        this.id = id;
        return this;
    }

    public DataEntity date(Instant date) {
        this.date = date;
        return this;
    }

    public DataEntity confirmed(Integer confirmed) {
        this.confirmed = confirmed;
        return this;
    }

    public DataEntity deaths(Integer deaths) {
        this.deaths = deaths;
        return this;
    }

    public DataEntity recovered(Integer recovered) {
        this.recovered = recovered;
        return this;
    }

    public DataEntity active(Integer active) {
        this.active = active;
        return this;
    }

    public DataEntity region(RegionEntity region) {
        this.region = region;
        return this;
    }

    public DataEntity merge(DataEntity data){
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
        if (!(o instanceof DataEntity)) {
            return false;
        }
        DataEntity dataEntity = (DataEntity) o;
        return Objects.equals(id, dataEntity.id) && Objects.equals(date, dataEntity.date) && Objects.equals(confirmed, dataEntity.confirmed) && Objects.equals(deaths, dataEntity.deaths) && Objects.equals(recovered, dataEntity.recovered) && Objects.equals(active, dataEntity.active) && Objects.equals(region, dataEntity.region);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, confirmed, deaths, recovered, active, region);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", date='" + getDate() + "'" +
            ", confirmed='" + getConfirmed() + "'" +
            ", deaths='" + getDeaths() + "'" +
            ", recovered='" + getRecovered() + "'" +
            ", active='" + getActive() + "'" +
            ", region='" + getRegion().getRegionName() + "'" +
            "}";
    }

}
