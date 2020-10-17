package com.pdcmb.covid19stats.presentation.models;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;

import org.springframework.http.HttpStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiErrorResponseModel {
    
    private int status;
    private String reason;
    private String message;

    public ApiErrorResponseModel() {}

    public ApiErrorResponseModel(HttpStatus status) {
        this.status = status.value();
        this.reason = status.getReasonPhrase();
    }

    public ApiErrorResponseModel(HttpStatus status, String message) {
        this.status = status.value();
        this.reason = status.getReasonPhrase();
        this.message = message;
    }


    public ApiErrorResponseModel(int status, String reason, String message) {
        this.status = status;
        this.reason = reason;
        this.message = message;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ApiErrorResponseModel status(int status) {
        this.status = status;
        return this;
    }

    public ApiErrorResponseModel reason(String reason) {
        this.reason = reason;
        return this;
    }

    public ApiErrorResponseModel message(String message) {
        this.message = message;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ApiErrorResponseModel)) {
            return false;
        }
        ApiErrorResponseModel apiErrorResponseModel = (ApiErrorResponseModel) o;
        return status == apiErrorResponseModel.status && Objects.equals(reason, apiErrorResponseModel.reason) && Objects.equals(message, apiErrorResponseModel.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, reason, message);
    }

    @Override
    public String toString() {
        return "{" +
            " status='" + getStatus() + "'" +
            ", reason='" + getReason() + "'" +
            ", message='" + getMessage() + "'" +
            "}";
    }
  

}
