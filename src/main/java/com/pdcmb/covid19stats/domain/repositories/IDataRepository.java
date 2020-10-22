package com.pdcmb.covid19stats.domain.repositories;

import com.pdcmb.covid19stats.domain.entities.Data;

import reactor.core.publisher.Flux;

public interface IDataRepository {
    
    Flux<Data> getAllData();

    Flux<Data> getDataByRegion(String regionCode);

    Flux<Long> saveData(Data... data);

    Flux<Long> deleteData(Data... data);

    Flux<Long> updateData(Data... data);
}
