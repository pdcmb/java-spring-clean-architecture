package com.pdcmb.covid19stats.presentation.controllers;

import javax.servlet.http.HttpServletRequest;

import com.pdcmb.covid19stats.presentation.exceptions.ResourceNotFoundException;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class HomeController {


    @GetMapping({"/", "/summary"})
    public String getRe() {
        throw new ResourceNotFoundException("Resource not found, use /resources to get al resources available resources");
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
    public Flux<Response> getMetadata() {
        return null;
    }
    
}
