package com.pdcmb.covid19stats.presentation.models;

import java.util.Objects;

import org.springframework.http.HttpMethod;

public class RouteResponseModel {

    private String route;
    private HttpMethod method;
    private String description;


    public RouteResponseModel() {
    }

    public RouteResponseModel(String route, HttpMethod method, String description) {
        this.route = route;
        this.method = method;
        this.description = description;
    }

    public String getRoute() {
        return this.route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public HttpMethod getMethod() {
        return this.method;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RouteResponseModel route(String route) {
        this.route = route;
        return this;
    }

    public RouteResponseModel method(HttpMethod method) {
        this.method = method;
        return this;
    }

    public RouteResponseModel description(String description) {
        this.description = description;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof RouteResponseModel)) {
            return false;
        }
        RouteResponseModel routeResponseModel = (RouteResponseModel) o;
        return Objects.equals(route, routeResponseModel.route) && Objects.equals(method, routeResponseModel.method) && Objects.equals(description, routeResponseModel.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(route, method, description);
    }

    @Override
    public String toString() {
        return "{" +
            " route='" + getRoute() + "'" +
            ", method='" + getMethod() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }

    
}
