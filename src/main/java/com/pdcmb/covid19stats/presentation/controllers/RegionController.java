package com.pdcmb.covid19stats.presentation.controllers;

import com.pdcmb.covid19stats.configuration.RegionsConfig;
import com.pdcmb.covid19stats.domain.usecases.GetRegionData;
import com.pdcmb.covid19stats.domain.usecases.GetRegionData.Request;
import com.pdcmb.covid19stats.domain.usecases.GetRegionData.Response;
import com.pdcmb.covid19stats.presentation.exceptions.ResourceNotFoundException;
import com.pdcmb.covid19stats.presentation.models.RegionResponseModel;
import com.pdcmb.covid19stats.presentation.models.RouteResponseModel;
import com.pdcmb.covid19stats.presentation.models.mappers.RegionMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@Component
@RestController
public class RegionController {

    private final RegionsConfig regionsConfig;
    private final RegionMapper regionMapper;

    private final GetRegionData getRegionData;
    
    @Autowired
    public RegionController(RegionsConfig regionsConfig, RegionMapper regionMapper,
                                                         GetRegionData getRegionData){
        this.regionsConfig = regionsConfig;
        this.regionMapper = regionMapper;
        this.getRegionData = getRegionData;

    }

    @GetMapping("/region")
    public Flux<RouteResponseModel>getAllData(@RequestParam(required = false) String filter) {
        return Flux.fromArray(new RouteResponseModel[]{
            new RouteResponseModel("region/:name", HttpMethod.GET, "returns daily  data for given region/s"),
            new RouteResponseModel("region/:name/latest", HttpMethod.GET, "return latest data for given region/s"),
            new RouteResponseModel("region/stats", HttpMethod.GET, "return data for given region"),
            new RouteResponseModel("regions", HttpMethod.GET, "return data for given region"),
        });
    }

    @GetMapping("/region/{region}")
    public Flux<RegionResponseModel> getData(@PathVariable String region,
                                             @RequestParam(required = false) String filter) {                                        
         return getRegionData.execute(new Request(region.equals("all") ? null : region, null ))
                             .flatMap(response -> regionMapper.transform(response.getRegion()));
    }

    @GetMapping("/region/{region}/latest")
    public Flux<RegionResponseModel> getLatestData(@PathVariable String region, @RequestParam(required = false) String filter) {
        return null;    
    }

    @GetMapping("/region/{region}/stats")
    public Flux<Response> getStats(@RequestParam(required = false) String name) {
        return null;
    }

    @GetMapping("/region/*")
    public void fallbackRoute() {
        throw new ResourceNotFoundException("Region non found, use /regions to list all regions available");
    }

    @GetMapping("/regions")
    public Flux<RegionResponseModel> getRegions() {
        return Flux.fromIterable(regionsConfig.getRegions().keySet())
                    .map(regionCode -> {
                        System.out.println(regionCode);
                        return new RegionResponseModel()
                                        .regionCode(regionCode)
                                        .regionName(regionsConfig.getRegions().get(regionCode).getName());
                    });
    }
    
}
