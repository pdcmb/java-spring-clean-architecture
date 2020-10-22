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
        return new RouteResponseModel("/region", HttpMethod.GET, "returns daily  data for given region/s");
    }

    /**
     * Default fallback route
     * 
     * @param request
     * @return
     */
    @GetMapping("*")
    public String fallbackRoute() {
        throw new ResourceNotFoundException("Resource not found, use /summary to get al resources available resources");
    }

    @GetMapping("/metadata")
    public String getMetadata() {
        return null;
    }


    
}
