package com.pdcmb.covid19stats.presentation.controllers;

import java.util.HashMap;
import java.util.Map;

import com.pdcmb.covid19stats.configuration.AppProperties;
import com.pdcmb.covid19stats.domain.entities.Filter;
import com.pdcmb.covid19stats.domain.usecases.FilterData;
import com.pdcmb.covid19stats.domain.usecases.GetDailyDelta;
import com.pdcmb.covid19stats.domain.usecases.GetDataStats;
import com.pdcmb.covid19stats.domain.usecases.GetRegionData;
import com.pdcmb.covid19stats.presentation.exceptions.ResourceNotFoundException;
import com.pdcmb.covid19stats.presentation.models.DataResponseModel;
import com.pdcmb.covid19stats.presentation.models.FieldResponseModel;
import com.pdcmb.covid19stats.presentation.models.RouteResponseModel;
import com.pdcmb.covid19stats.presentation.models.StatsResponseModel;
import com.pdcmb.covid19stats.presentation.models.mappers.DataToDataResponse;
import com.pdcmb.covid19stats.presentation.models.mappers.JsonToFilter;
import com.pdcmb.covid19stats.presentation.models.mappers.StatsToStatsResponse;

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
    private final StatsToStatsResponse statsToStatsResponse;

    private final GetRegionData getRegion;
    private final GetDailyDelta getDailyData;
    private final FilterData filterData;
    private final GetDataStats getDataStats;
    
    @Autowired
    public RegionController(AppProperties appProperties, JsonToFilter jsonToFilter, 
                            DataToDataResponse dataToDataResponse, StatsToStatsResponse statsToStatsResponse,
                            GetRegionData getRegion, FilterData filterData, GetDataStats getDataStats,
                            GetDailyDelta getDailyDelta){
        this.appProperties = appProperties;

        this.jsonToFilter = jsonToFilter;
        this.dataToDataResponse = dataToDataResponse;
        this.statsToStatsResponse = statsToStatsResponse;

        this.getRegion = getRegion;
        this.getDailyData = getDailyDelta;
        this.filterData = filterData;
        this.getDataStats = getDataStats;
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
        Filter[] filters = jsonToFilter.map(filter);
        return getRegion.execute(new GetRegionData.Request(region.equals("all") ? null : region))
                        .map(response -> response.getRegion())
                        .flatMap(reg -> filterData.execute(new FilterData.Request(reg, filters)))
                        .flatMap(response -> Flux.fromIterable(response.getRegion().getData()))
                        .map(response -> dataToDataResponse.map(response));
    }

    @GetMapping("/region/{region}/latest")
    public Flux<DataResponseModel> getLatestRegionData(@PathVariable String region,
                                                @RequestParam(required = false) String filter) {
        Filter[] filters = jsonToFilter.map(filter);
        return getRegion.execute(new GetRegionData.Request(region.equals("all") ? null : region))
                        .map(response -> response.getRegion())
                        .flatMap(reg -> filterData.execute(new FilterData.Request(reg, filters)))
                        .flatMap(response -> 
                                Flux.fromIterable(response.getRegion().getData())
                                    .reduce((first, second) -> second)
                        )
                        .map(data -> dataToDataResponse.map(data));

    }

    @GetMapping("/region/{region}/stats")
    public Flux<StatsResponseModel> getStats(@PathVariable String region, @RequestParam String field, 
                                    @RequestParam(required = false) String filter){
        Filter[] filters = jsonToFilter.map(filter);
        return getRegion.execute(new GetRegionData.Request(region.equals("all") ? null : region))
                        .map(response -> response.getRegion())
                        .flatMap(reg -> filterData.execute(new FilterData.Request(reg, filters)))
                        .flatMap(response -> Flux.just(response.getRegion()))
                        .collectList()
                        .flatMap(regions -> getDataStats.execute(new GetDataStats.Request(regions, field)))
                        .flux()
                        .map(response -> statsToStatsResponse.map(response.getStats())); 
    }

    @GetMapping("/region/{region}/delta")
    public Flux<DataResponseModel> getRegionDelta(@PathVariable String region,
                                            @RequestParam(required = false) String filter) {
        Filter[] filters = jsonToFilter.map(filter);
        return getDailyData.execute(new GetDailyDelta.Request(region.equals("all") ? null : region))
                        .map(response -> response.getRegion())
                        .flatMap(reg -> filterData.execute(new FilterData.Request(reg, filters)))
                        .flatMap(response -> Flux.fromIterable(response.getRegion().getData()))
                        .map(response -> dataToDataResponse.map(response));
    }


    @GetMapping("/region/{region}/delta/stats")
    public Flux<StatsResponseModel> getDeltaStats(@PathVariable String region, @RequestParam String field, 
                                    @RequestParam(required = false) String filter){
        Filter[] filters = jsonToFilter.map(filter);
        return getDailyData.execute(new GetDailyDelta.Request(region.equals("all") ? null : region))
                        .map(response -> response.getRegion())
                        .flatMap(reg -> filterData.execute(new FilterData.Request(reg, filters)))
                        .flatMap(response -> Flux.just(response.getRegion()))
                        .collectList()
                        .flatMap(regions -> getDataStats.execute(new GetDataStats.Request(regions, field)))
                        .flux()
                        .map(response -> statsToStatsResponse.map(response.getStats())); 
    }

    @GetMapping("/region/metadata")
    public Flux<FieldResponseModel> getMetadata() {
        return Flux.fromIterable(DataResponseModel.Metdata.METADATA);
    }

    @GetMapping("/region/*")
    public void fallbackRoute() {
        throw new ResourceNotFoundException("Region non found, use /regions to get all available regions");
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
