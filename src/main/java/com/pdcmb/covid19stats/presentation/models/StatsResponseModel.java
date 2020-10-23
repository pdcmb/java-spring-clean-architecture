package com.pdcmb.covid19stats.presentation.models;

import java.util.Objects;

public class StatsResponseModel {

    private String field;
    private Integer count;
    private Integer average;
    private Integer min;
    private Integer max;
    private Integer sum;
    

    public StatsResponseModel() {
    }

    public StatsResponseModel(String field, Integer count, Integer average, Integer min, Integer max, Integer sum) {
        this.field = field;
        this.count = count;
        this.average = average;
        this.min = min;
        this.max = max;
        this.sum = sum;
    }

    public String getField() {
        return this.field;
    }

    public void setField(String field) {
        this.field = field;
    }
    
    public Integer getCount() {
        return this.count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
    public Integer getAverage() {
        return this.average;
    }

    public void setAverage(Integer average) {
        this.average = average;
    }

    public Integer getMin() {
        return this.min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return this.max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Integer getSum() {
        return this.sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public StatsResponseModel field(String field) {
        this.field = field;
        return this;
    }

    public StatsResponseModel average(Integer average) {
        this.average = average;
        return this;
    }

    public StatsResponseModel min(Integer min) {
        this.min = min;
        return this;
    }

    public StatsResponseModel max(Integer max) {
        this.max = max;
        return this;
    }

    public StatsResponseModel sum(Integer sum) {
        this.sum = sum;
        return this;
    }

    public StatsResponseModel count(Integer count) {
        this.count = count;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof StatsResponseModel)) {
            return false;
        }
        StatsResponseModel statsResponseModel = (StatsResponseModel) o;
        return Objects.equals(field, statsResponseModel.field) && Objects.equals(average, statsResponseModel.average) && Objects.equals(min, statsResponseModel.min) && Objects.equals(max, statsResponseModel.max) && Objects.equals(sum, statsResponseModel.sum) && Objects.equals(count, statsResponseModel.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(field, average, min, max, sum, count);
    }

    @Override
    public String toString() {
        return "{" +
            " field='" + getField() + "'" +
            ", average='" + getAverage() + "'" +
            ", min='" + getMin() + "'" +
            ", max='" + getMax() + "'" +
            ", sum='" + getSum() + "'" +
            ", count='" + getCount() + "'" +
            "}";
    }
 


}
