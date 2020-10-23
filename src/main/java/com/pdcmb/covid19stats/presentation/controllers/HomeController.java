package com.pdcmb.covid19stats.presentation.controllers;

import com.pdcmb.covid19stats.presentation.exceptions.ResourceNotFoundException;
import com.pdcmb.covid19stats.presentation.models.RouteResponseModel;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class HomeController {

    @GetMapping({ "/", "/summary" })
    public RouteResponseModel getResources() {
        return new RouteResponseModel("/region", HttpMethod.GET, "Data on Covid-19 for given region/s");
    }

    @GetMapping("*")
    public String fallbackRoute() {
        throw new ResourceNotFoundException("Resource not found, use /summary to get al resources available resources");
    }
    
}
