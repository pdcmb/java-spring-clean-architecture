package com.pdcmb.covid19stats.data.repositories;

import java.time.Duration;

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

@Repository
public class RegionRepositoryImpl implements IRegionRepository {

    private final Covid19Api restApi;
    private final RegionsConfig regionsConfig;

    @Autowired
    public RegionRepositoryImpl(Covid19Api restApi, RegionsConfig regionsConfig){
        this.restApi = restApi;
        this.regionsConfig = regionsConfig;
    }

    @Override
    public Flux<Region> getRegionData(String... regionCodes) {
        return Flux.fromArray(regionCodes)
                    .map(regionCode -> {
                        return Flux.fromArray(regionsConfig.getRegions().get(regionCode)
                                                    .getCountries().toArray(String[]::new))
                                    .delayElements(Duration.ofSeconds(2))
                                    .map(countryString -> {
                                        Country country = new Country();
                                        return restApi.getCountryData(countryString)
                                                    .map(response ->{
                                                            if(country.getName() != null || country.getCountryCode() != null)
                                                                country.name(response.getCountry())
                                                                        .setCountryCode(response.getCountryCode());
                                                            return new Data()
                                                                    .date(response.getDate())
                                                                    .confirmed(response.getConfirmed())
                                                                    .deaths(response.getDeaths())
                                                                    .recovered(response.getRecovered())
                                                                    .active(response.getActive());
                                                    })
                                                    .collectList()
                                                    .as(dataMono -> {
                                                        return country.data(dataMono.block());  
                                                    });
                                    })
                                    .collectList()
                                    .as(countryMono -> {
                                        return new Region().name(regionsConfig.getRegions().get(regionCode).getName())
                                                            .countries(countryMono.block());
                                    });
                    });
                        
    }

    @Override
    public Flux<Region> getAllRegionData() {
        throw new UnsupportedOperationException();
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
