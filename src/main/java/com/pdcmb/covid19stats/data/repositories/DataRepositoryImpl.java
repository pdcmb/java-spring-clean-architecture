package com.pdcmb.covid19stats.data.repositories;


import com.pdcmb.covid19stats.data.datasources.Covid19Api;
import com.pdcmb.covid19stats.data.datasources.RegionCache;
import com.pdcmb.covid19stats.data.entities.mappers.RegionEntityToRegion;
import com.pdcmb.covid19stats.domain.entities.Region;
import com.pdcmb.covid19stats.domain.entities.Data;
import com.pdcmb.covid19stats.domain.repositories.IDataRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;

@Repository
public class DataRepositoryImpl implements IDataRepository {

    private final Covid19Api restApi;
    private final RegionCache regionCache;

    private final RegionEntityToRegion regionEntityToRegion; 

    @Autowired
    public DataRepositoryImpl(Covid19Api restApi, RegionCache regionCache, 
                                RegionEntityToRegion regionEntityToRegion){
        this.restApi = restApi;
        this.regionCache = regionCache;
        this.regionEntityToRegion = regionEntityToRegion;
    }
    @Override
    public Flux<Data> getAllData() {
        return regionCache.getAll()
                        .switchIfEmpty(
                            restApi.getRegions()
                                    .doOnNext(regionCache::add)
                        )
                        .map(regionEntityToRegion::map)
                        .flatMap(region -> Flux.fromIterable(region.getData()));
    }

    @Override
    public Flux<Data> getDataByRegion(String regionCode) {
        return regionCache.get(regionCode)
                        .switchIfEmpty(
                            restApi.getRegions(regionCode)
                                    .doOnNext(regionCache::add)
                        )
                        .map(regionEntityToRegion::map)
                        .flatMap(region -> Flux.fromIterable(region.getData()));    
    }

    @Override
    public Flux<Long> saveData(Data... data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Flux<Long> deleteData(Data... data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Flux<Long> updateData(Data... data) {
        throw new UnsupportedOperationException();
    }

    
    
}
