package com.pdcmb.covid19stats.domain.repositories;

import com.pdcmb.covid19stats.domain.entities.Region;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface IRegionRepository {
    
    Flux<Region> getRegionData(String... regionCodes);

    Flux<Region> getAllRegionData();

    Mono<Long> saveRegion(String regionCode);

    Mono<Long> deleteRegion(String regionCode);

    Mono<Long> updateRegion(String regionCode);
    
}
