package com.pdcmb.covid19stats.presentation.models.mappers;


import com.pdcmb.covid19stats.domain.entities.Data;
import com.pdcmb.covid19stats.domain.entities.Region;
import com.pdcmb.covid19stats.presentation.models.RegionResponseModel;

import org.springframework.stereotype.Component;

import reactor.core.publisher.Flux;

@Component
public class RegionMapper {

    /**
     * Transform {@link Region} instance into stream of {@link RegionResponseModel} instances.
     * 
     * @param region
     * @return
     */
    public Flux<RegionResponseModel> transform(Region region){
        return Flux.fromIterable(region.getCountries())
                    .reduce(0, (maxSize, country) ->{ // since not all data sets are equal we want to find th longest one
                        if(country.getData().size() > maxSize)
                            return country.getData().size();
                        else return maxSize;
                    }).flux()
                    .flatMap(dataSize -> {
                        return Flux.range(0, dataSize)
                                    .flatMap( i -> {
                                        return Flux.fromIterable(region.getCountries())
                                                    .filter(country -> country.getData().size() > i ) // get country only if there is data left
                                                    .reduce(new RegionResponseModel()
                                                                    .regionCode(region.getRegionCode())
                                                                    .regionName(region.getName()),
                                                            (response, country) -> {
                                                            Data dayData = country.getData().get(i);
                                                            if(response.getDate() == null)
                                                                response.setDate(dayData.getDate());
                                                            return response.confirmed(response.getConfirmed() + dayData.getConfirmed())
                                                                        .deaths(response.getDeaths() + dayData.getDeaths())
                                                                        .recovered(response.getRecovered() + dayData.getRecovered())
                                                                        .active(response.getActive() + dayData.getActive());
                                                    });
                                    });                    
                    });
    }
    
}
