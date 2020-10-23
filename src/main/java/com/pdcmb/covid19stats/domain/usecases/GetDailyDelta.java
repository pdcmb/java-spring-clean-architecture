package com.pdcmb.covid19stats.domain.usecases;

import java.util.ArrayList;
import java.util.List;

import com.pdcmb.covid19stats.domain.entities.Data;
import com.pdcmb.covid19stats.domain.entities.Region;
import com.pdcmb.covid19stats.domain.repositories.IRegionRepository;
import com.pdcmb.covid19stats.domain.usecases.base.BaseFluxUseCase;
import com.pdcmb.covid19stats.domain.usecases.base.IRequest;
import com.pdcmb.covid19stats.domain.usecases.base.IResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Flux;

/**
 * Retrieves data from repository and calculates daily increment of confimed, deaths, recovered and active
 * and returns {@link Region} containing new data
 * 
 * @author Mateusz Ziomek
 */
@Component
public class GetDailyDelta extends BaseFluxUseCase<GetDailyDelta.Request, GetDailyDelta.Response> {

    private final IRegionRepository regionRepository;

    @Autowired
    public GetDailyDelta(IRegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @Override
    protected Flux<Response> createPublisher(Request request) {

        Flux<Region> stream;

        if(request.getRegionCode() == null){
            stream = regionRepository.getAllRegions();
        } else{
            stream = regionRepository.getRegionbyCode(request.getRegionCode());
        }   
        return stream.flatMap(region -> {
                        return Flux.just(region.getData())
                                .map(dataList -> { 

                                    List<Data> dailyData = new ArrayList<>();

                                    for(int i = 0; i < dataList.size(); i++){
                                        Data currentData = dataList.get(i);
                                        if(i == 0){
                                            dailyData.add(currentData);
                                            continue;
                                        } 
                                        Data prevData = dataList.get(i - 1);
                                        dailyData.add(new Data(
                                            currentData.getRegion(),
                                            currentData.getDate(),
                                            currentData.getConfirmed() - prevData.getConfirmed(),
                                            currentData.getDeaths() - prevData.getDeaths(),
                                            currentData.getRecovered() - prevData.getRecovered(),
                                            currentData.getConfirmed() - prevData.getConfirmed()
                                        ));
                                    }
                                    return dailyData;    
                                })
                                .map( dataList -> region.data(dataList));

        })
        .map(Response::new);
    }

 
    public static class Request implements IRequest{

        private String regionCode;

        public Request(){}

        public Request(String regionCode){
            this.regionCode = regionCode;
        }

        public String getRegionCode(){
            return this.regionCode;
        }

    }

    public static class Response implements IResponse{

        private Region region;

        public Response(Region region){
            this.region = region;
        }

        public Region getRegion(){
            return this.region;
        }

    }
}
