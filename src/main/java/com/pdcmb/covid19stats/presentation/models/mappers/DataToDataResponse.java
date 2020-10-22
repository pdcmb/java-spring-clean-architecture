package com.pdcmb.covid19stats.presentation.models.mappers;

import com.pdcmb.covid19stats.domain.entities.Data;
import com.pdcmb.covid19stats.presentation.models.DataResponseModel;

import org.springframework.stereotype.Component;

@Component
public class DataToDataResponse {
    
    public DataResponseModel map(Data data){
        return new DataResponseModel(
            data.getRegion().getRegionCode(),
            data.getRegion().getName(),
            data.getDate(),
            data.getConfirmed(),
            data.getDeaths(),
            data.getRecovered(),
            data.getActive());
    }
}
