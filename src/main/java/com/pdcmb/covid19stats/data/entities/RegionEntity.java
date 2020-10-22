package com.pdcmb.covid19stats.data.entities;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name ="regions")
public class RegionEntity {
    
    @Id
    private String regionCode;

    private String regionName;

    @OneToMany(mappedBy = "region", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<DataEntity> data;

    public RegionEntity() {
    }

    public RegionEntity(RegionEntity regionEntity) {
        this(regionEntity.getRegionCode(), regionEntity.getRegionName(), regionEntity.getData());
    }

    public RegionEntity(String regionCode, String regionName, List<DataEntity> data) {
        this.regionCode = regionCode;
        this.regionName = regionName;
        this.data = data;
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

    public List<DataEntity> getData() {
        return this.data;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public RegionEntity regionCode(String regionCode) {
        this.regionCode = regionCode;
        return this;
    }

    public RegionEntity regionName(String regionName) {
        this.regionName = regionName;
        return this;
    }

    public RegionEntity data(List<DataEntity> data) {
        this.data = data;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof RegionEntity)) {
            return false;
        }
        RegionEntity regionEntity = (RegionEntity) o;
        return Objects.equals(regionCode, regionEntity.regionCode) && Objects.equals(regionName, regionEntity.regionName) && Objects.equals(data, regionEntity.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(regionCode, regionName, data);
    }

    @Override
    public String toString() {
        return "{" +
            " regionCode='" + getRegionCode() + "'" +
            ", regionName='" + getRegionName() + "'" +
            ", data='" + getData() + "'" +
            "}";
    }


}
