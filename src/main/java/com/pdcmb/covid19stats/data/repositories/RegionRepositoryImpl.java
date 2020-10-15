package com.pdcmb.covid19stats.data.repositories;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;

import com.pdcmb.covid19stats.configuration.RegionsConfig;
import com.pdcmb.covid19stats.data.datasources.Covid19Api;
import com.pdcmb.covid19stats.domain.entities.Region;
import com.pdcmb.covid19stats.domain.entities.Country;
import com.pdcmb.covid19stats.domain.entities.Data;
import com.pdcmb.covid19stats.domain.repositories.IRegionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Region repository, it retrieves {@link Region} entities
 * 
 * @author Mateusz Ziomek
 */

@Repository
public class RegionRepositoryImpl implements IRegionRepository {

    private final Covid19Api restApi;
    private final RegionsConfig regionsConfig;

    @Autowired
    public RegionRepositoryImpl(Covid19Api restApi, RegionsConfig regionsConfig){
        this.restApi = restApi;
        this.regionsConfig = regionsConfig;
    }

    /**
     * Creates stream of {@link Region} objects 
     * 
     */
    @Override
    public Flux<Region> getRegionData(String... regionCodes) {
        return Flux.fromArray(regionCodes)
                    .flatMap(regionCode -> {
                        return Flux.fromIterable(regionsConfig.getRegions().get(regionCode).getCountries())
                                    .delayElements(Duration.ofSeconds(2))               // Otherwise Covid19api will block to many requests
                                    .flatMap(countryString -> {
                                        return restApi.getCountryData(countryString)
                                                      .reduce(new Country().data(new ArrayList<>()), (country, response) -> {
                                                            if(country.getName() == null || country.getName().isEmpty())
                                                                country.name(response.getCountry())
                                                                        .setCountryCode(response.getCountryCode());
                                                            country.getData().add(new Data().date(response.getDate())
                                                                                            .confirmed(response.getConfirmed())
                                                                                            .deaths(response.getDeaths())
                                                                                            .recovered(response.getRecovered())
                                                                                            .active(response.getActive()));
                                                            return country;
                                                      });
                                    })
                                    .map(country -> {                                   //Reverse order
                                        Collections.reverse(country.getData());
                                        return country;
                                    })                                
                                    .reduce(new Region().name(regionsConfig.getRegions().get(regionCode).getName()),
                                            (region, country) -> {
                                                region.getCountries().add(country);
                                                return region;  
                                    });
                    }).cache();
                        
    }


    @Override
    public Flux<Region> getAllRegionData() {
        return Flux.fromIterable(regionsConfig.getRegions().values())
                    .flatMap(reg -> {
                        return Flux.fromIterable(reg.getCountries())
                                    .delayElements(Duration.ofSeconds(2))
                                    .flatMap(countryString -> {
                                        return restApi.getCountryData(countryString)
                                                    .reduce(new Country().data(new ArrayList<>()), (country, response) -> {
                                                            if(country.getName() == null || country.getName().isEmpty())
                                                                country.name(response.getCountry())
                                                                        .setCountryCode(response.getCountryCode());
                                                            country.getData().add(new Data().date(response.getDate())
                                                                                            .confirmed(response.getConfirmed())
                                                                                            .deaths(response.getDeaths())
                                                                                            .recovered(response.getRecovered())
                                                                                            .active(response.getActive()));
                                                            return country;
                                                    });
                                    })
                                    .map(country -> {
                                        Collections.reverse(country.getData());
                                        return country;
                                    })
                                    .reduce(new Region().name(reg.getName()),(region, country) -> {
                                                region.getCountries().add(country);
                                                return region;  
                    });
        }).cache();
    }

    @Override
    public Mono<Long> saveRegion(String regionCode) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Mono<Long> deleteRegion(String regionCode) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Mono<Long> updateRegion(String regionCode) {
        throw new UnsupportedOperationException();
    }

    
    
}
