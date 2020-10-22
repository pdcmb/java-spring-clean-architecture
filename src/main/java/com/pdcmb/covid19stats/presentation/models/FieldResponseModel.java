package com.pdcmb.covid19stats.presentation.models;

import java.util.Objects;

public class FieldResponseModel {

    private String fieldName;

    private String description;

    private String type;


    public FieldResponseModel() {
    }

    public FieldResponseModel(String fieldName, String description, String type) {
        this.fieldName = fieldName;
        this.description = description;
        this.type = type;
    }

    public String getFieldName() {
        return this.fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public FieldResponseModel fieldName(String fieldName) {
        this.fieldName = fieldName;
        return this;
    }

    public FieldResponseModel description(String description) {
        this.description = description;
        return this;
    }

    public FieldResponseModel type(String type) {
        this.type = type;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof FieldResponseModel)) {
            return false;
        }
        FieldResponseModel fieldResponseModel = (FieldResponseModel) o;
        return Objects.equals(fieldName, fieldResponseModel.fieldName) && Objects.equals(description, fieldResponseModel.description) && Objects.equals(type, fieldResponseModel.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fieldName, description, type);
    }

    @Override
    public String toString() {
        return "{" +
            " fieldName='" + getFieldName() + "'" +
            ", description='" + getDescription() + "'" +
            ", type='" + getType() + "'" +
            "}";
    }

    
}
