package com.pdcmb.covid19stats.domain.entities;

import java.util.Objects;

/**
 * 
 * @author Mateusz Ziomek
 */
public class Stats {

    private String field;
    private Integer average;
    private Integer min;
    private Integer max;
    private Integer sum;
    private Integer count;


    public Stats() {
    }

    public Stats(String field, Integer average, Integer min, Integer max, Integer sum, Integer count) {
        this.field = field;
        this.average = average;
        this.min = min;
        this.max = max;
        this.sum = sum;
        this.count = count;
    }

    public String getField() {
        return this.field;
    }

    public void setField(String field) {
        this.field = field;
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

    public Integer getCount() {
        return this.count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Stats field(String field) {
        this.field = field;
        return this;
    }

    public Stats average(Integer average) {
        this.average = average;
        return this;
    }

    public Stats min(Integer min) {
        this.min = min;
        return this;
    }

    public Stats max(Integer max) {
        this.max = max;
        return this;
    }

    public Stats sum(Integer sum) {
        this.sum = sum;
        return this;
    }

    public Stats count(Integer count) {
        this.count = count;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Stats)) {
            return false;
        }
        Stats stats = (Stats) o;
        return Objects.equals(field, stats.field) && Objects.equals(average, stats.average) && Objects.equals(min, stats.min) && Objects.equals(max, stats.max) && Objects.equals(sum, stats.sum) && Objects.equals(count, stats.count);
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
