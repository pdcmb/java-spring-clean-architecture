package com.pdcmb.covid19stats.presentation.models.mappers;

import com.pdcmb.covid19stats.domain.entities.Stats;
import com.pdcmb.covid19stats.presentation.models.StatsResponseModel;

import org.springframework.stereotype.Component;

@Component
public class StatsToStatsResponse {

    public StatsResponseModel map(Stats stats){
        return new StatsResponseModel(
            stats.getField(),
            stats.getCount(),
            stats.getAverage(),
            stats.getMin(),
            stats.getMax(),
            stats.getSum()
        );
    }
}
