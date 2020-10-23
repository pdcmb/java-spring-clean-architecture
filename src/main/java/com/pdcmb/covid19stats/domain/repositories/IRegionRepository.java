package com.pdcmb.covid19stats.domain.repositories;

import com.pdcmb.covid19stats.domain.entities.Region;

import reactor.core.publisher.Flux;

public interface IRegionRepository {

    Flux<Region> getAllRegions();

    Flux<Region> getRegionbyCode(String regionCode);

    Flux<Long> saveRegion(Region... data);

    Flux<Long> deleteRegion(Region... data);

    Flux<Long> updateRegion(Region... data);
}
