package com.pdcmb.covid19stats.presentation.controllers;

import java.util.HashMap;
import java.util.Map;

import com.pdcmb.covid19stats.configuration.AppProperties;
import com.pdcmb.covid19stats.domain.usecases.GetData;
import com.pdcmb.covid19stats.domain.usecases.GetData.Request;
import com.pdcmb.covid19stats.presentation.exceptions.ResourceNotFoundException;
import com.pdcmb.covid19stats.presentation.models.DataResponseModel;
import com.pdcmb.covid19stats.presentation.models.RouteResponseModel;
import com.pdcmb.covid19stats.presentation.models.StatsResponseModel;
import com.pdcmb.covid19stats.presentation.models.mappers.DataToDataResponse;
import com.pdcmb.covid19stats.presentation.models.mappers.JsonToFilter;

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

    private final AppProperties appProperties;

    private final JsonToFilter jsonToFilter;
    private final DataToDataResponse dataToDataResponse;

    private final GetData getData;
    
    @Autowired
    public RegionController(AppProperties appProperties, JsonToFilter jsonToFilter,
                            DataToDataResponse dataToDataResponse, GetData getData){
        this.appProperties = appProperties;
        this.jsonToFilter = jsonToFilter;
        this.dataToDataResponse = dataToDataResponse;
        this.getData = getData;
    }

    @GetMapping("/region")
    public Flux<RouteResponseModel>getAllData() {
        return Flux.fromArray(new RouteResponseModel[]{
            new RouteResponseModel("region/:name", HttpMethod.GET, "returns daily  data for given region/s"),
            new RouteResponseModel("region/:name/latest", HttpMethod.GET, "return latest data for given region/s"),
            new RouteResponseModel("region/stats", HttpMethod.GET, "return data for given region"),
            new RouteResponseModel("regions", HttpMethod.GET, "return data for given region"),
        });
    }

    @GetMapping("/region/{region}")
    public Flux<DataResponseModel> getRegionData(@PathVariable String region,
                                            @RequestParam(required = false) String filter) {
        return getData.execute(new Request(region.equals("all") ? null : region,
                                            jsonToFilter.map(filter)))
                            .map(response -> dataToDataResponse.map(response.getRegion()));
    }

    @GetMapping("/region/{region}/latest")
    public Flux<DataResponseModel> getLatestRegionData(@PathVariable String region,
                                                @RequestParam(required = false) String filter) {
        return getData.execute(new Request(region.equals("all") ? null : region,
                                            jsonToFilter.map(filter)))
                            .map(response -> dataToDataResponse.map(response.getRegion()));    
    }

    @GetMapping("/region/{region}/stats")
    public Flux<StatsResponseModel> getStats(@PathVariable String name, @RequestParam String field, 
                                    @RequestParam(required = false) String filter){
        return null;
    }

    @GetMapping("/region/*")
    public void fallbackRoute() {
        throw new ResourceNotFoundException("Region non found, use /regions to get all regions available");
    }

    @GetMapping("/regions")
    public Flux<Map<String,String>> getRegions() {
        return Flux.fromIterable(appProperties.getRegions())
                    .map(region -> {
                        Map<String,String> regionsMap = new HashMap<>();
                        regionsMap.put("region code", region.getCode());
                        regionsMap.put("region name", region.getName());
                        return regionsMap;
                    });
    }
    
}
