package com.pdcmb.covid19stats.web.controllers;

import com.pdcmb.covid19stats.domain.usecases.GetRegionData;
import com.pdcmb.covid19stats.domain.usecases.GetRegionData.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
@Component
public class RegionController {

    private final GetRegionData getRegionData;
    
    @Autowired
    public RegionController(GetRegionData getRegionData){
        this.getRegionData = getRegionData;
    }

    @GetMapping("/region")
    public Flux<Response>getRegion(@RequestParam(required = false) String name) {
        return getRegionData.execute(null);
    }
    
}
