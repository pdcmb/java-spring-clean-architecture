package com.pdcmb.covid19stats.data.entities.mappers;

import com.pdcmb.covid19stats.data.entities.DataEntity;
import com.pdcmb.covid19stats.domain.entities.Data;
import com.pdcmb.covid19stats.domain.entities.Region;

import org.springframework.stereotype.Component;

@Component
public class DataEntityToData {

    public Data map(DataEntity dataEntity, Region region){
        return new Data(
            region,
            dataEntity.getDate(),
            dataEntity.getConfirmed(),
            dataEntity.getDeaths(),
            dataEntity.getRecovered(),
            dataEntity.getActive()
        );
    }
    
}
