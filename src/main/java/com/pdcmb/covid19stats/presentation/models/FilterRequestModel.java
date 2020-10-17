package com.pdcmb.covid19stats.presentation.models;

import java.util.Objects;

public class FilterRequestModel {

    private String field;
    private String operator;
    private Object value;


    public FilterRequestModel() {
    }

    public FilterRequestModel(String field, String operator, Object value) {
        this.field = field;
        this.operator = operator;
        this.value = value;
    }

    public String getField() {
        return this.field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getOperator() {
        return this.operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Object getValue() {
        return this.value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public FilterRequestModel field(String field) {
        this.field = field;
        return this;
    }

    public FilterRequestModel operator(String operator) {
        this.operator = operator;
        return this;
    }

    public FilterRequestModel value(Object value) {
        this.value = value;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof FilterRequestModel)) {
            return false;
        }
        FilterRequestModel filterRequestModel = (FilterRequestModel) o;
        return Objects.equals(field, filterRequestModel.field) && Objects.equals(operator, filterRequestModel.operator) && Objects.equals(value, filterRequestModel.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(field, operator, value);
    }

    @Override
    public String toString() {
        return "{" +
            " field='" + getField() + "'" +
            ", operator='" + getOperator() + "'" +
            ", value='" + getValue() + "'" +
            "}";
    }

    
}
