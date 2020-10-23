package com.pdcmb.covid19stats.data.datasources;

import java.time.Duration;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.pdcmb.covid19stats.configuration.AppProperties;
import com.pdcmb.covid19stats.data.datasources.dto.ResponseEntity;
import com.pdcmb.covid19stats.data.entities.DataEntity;
import com.pdcmb.covid19stats.data.entities.RegionEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;

@Component
public class Covid19Api {

    private final WebClient webClient;
    private final AppProperties appProperties;

    @Autowired
    public Covid19Api(WebClient webClient, AppProperties appProperties){
        this.webClient = webClient;
        this.appProperties = appProperties;
    }



    /**
     * Method that retrieves data from Covid19api.
     * It makes a request for every country in given region, data are then grouped
     * and merged into a {@link RegionEntity} 
     * 
     * 
     * @param codes Region codes of regions to be retrevied
     * @return A {@link Flux} that emits requsted region {@link RegionEntity entities}
     */
	public Flux<RegionEntity> getRegions(String... codes) {
        List<String> regionCodes;
        if (codes.length == 0){
            regionCodes = appProperties.getRegions().stream()
                                    .map(region -> { return region.getCode();} )
                                    .collect(Collectors.toList());
        } else {
            regionCodes = Arrays.asList(codes);
        }
        return Flux.fromIterable(regionCodes)
                    .map(regionCode -> {
                        return appProperties.getRegions().stream()
                                    .filter(region -> region.getCode().equals(regionCode))
                                    .findFirst()
                                    .get(); 
                    })
                    .flatMap(region -> {
                        return Flux.fromIterable(region.getCountries())
                                    .delayElements(Duration.ofSeconds(2))
                                    .flatMap( country -> {
                                            return webClient
                                                    .get()
                                                    .uri("/country/" + country)
                                                    .retrieve()
                                                    .bodyToFlux(ResponseEntity.class)
                                                    .map(response -> {
                                                        return new DataEntity()
                                                                    .date(response.getDate())
                                                                    .confirmed(response.getConfirmed())
                                                                    .deaths(response.getDeaths())
                                                                    .recovered(response.getRecovered())
                                                                    .active(response.getActive());
                                                    });

                                    })
                                    .collect(Collectors.groupingBy(DataEntity::getDate))
                                        .flatMap(map -> {
                                            return Flux.fromIterable(map.keySet())
                                                        .flatMap(key -> {
                                                            return Flux.fromIterable(map.get(key))
                                                                        .reduce(new DataEntity(), 
                                                                            (total, partial) -> {
                                                                                if(total.equals(new DataEntity()))
                                                                                    return new DataEntity(partial);
                                                                                else
                                                                                    return total.merge(partial);
                                                                            });
                                                        })
                                                        .sort((d1, d2) -> d1.getDate().compareTo(d2.getDate()))
                                                        .collectList();
                                    })
                                    .map(data -> {
                                        return new RegionEntity(region.getCode(), region.getName(), data);
                                    });

                    });
	}
    
}
