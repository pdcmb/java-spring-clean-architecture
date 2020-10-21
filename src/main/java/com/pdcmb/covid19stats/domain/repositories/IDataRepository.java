package com.pdcmb.covid19stats.domain.repositories;

import com.pdcmb.covid19stats.domain.entities.Data;
import com.pdcmb.covid19stats.domain.entities.Region;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IDataRepository {
    
    Flux<Data> getAllData();

    Flux<Data> getDataByRegion(String regionCode);

    Mono<Long> saveData(Data... data);

    Mono<Long> deleteData(Data... data);

    Mono<Long> updateData(Data... data);
}
