package com.pdcmb.covid19stats.data.repositories;

import com.pdcmb.covid19stats.data.datasources.Covid19Api;
import com.pdcmb.covid19stats.data.datasources.RegionCache;
import com.pdcmb.covid19stats.data.entities.mappers.RegionEntityToRegion;
import com.pdcmb.covid19stats.domain.entities.Region;
import com.pdcmb.covid19stats.domain.repositories.IRegionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Flux;

@Component
public class RegionRepositoryImpl implements IRegionRepository {

    private final Covid19Api restApi;
    private final RegionCache regionCache;

    private final RegionEntityToRegion regionEntityToRegion; 

    @Autowired
    public RegionRepositoryImpl(Covid19Api restApi, RegionCache regionCache, 
                                RegionEntityToRegion regionEntityToRegion){
        this.restApi = restApi;
        this.regionCache = regionCache;
        this.regionEntityToRegion = regionEntityToRegion;
    }

    @Override
    public Flux<Region> getAllRegions() {
        return regionCache.getAll()
            .switchIfEmpty(
                restApi.getRegions()
                        .doOnNext(regionCache::add)
            )
            .map(regionEntityToRegion::map);
    }

    @Override
    public Flux<Region> getRegionbyCode(String regionCode) {
        return regionCache.get(regionCode)
            .switchIfEmpty(
                restApi.getRegions(regionCode)
                        .doOnNext(regionCache::add)
            )
            .map(regionEntityToRegion::map);
    }

    @Override
    public Flux<Long> saveRegion(Region... data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Flux<Long> deleteRegion(Region... data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Flux<Long> updateRegion(Region... data) {
        throw new UnsupportedOperationException();
    }
    
}
