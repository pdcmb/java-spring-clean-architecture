package com.pdcmb.covid19stats.domain.usecases;

import java.util.ArrayList;

import com.pdcmb.covid19stats.domain.entities.Data;
import com.pdcmb.covid19stats.domain.entities.Filter;
import com.pdcmb.covid19stats.domain.entities.Region;
import com.pdcmb.covid19stats.domain.repositories.IRegionRepository;
import com.pdcmb.covid19stats.domain.usecases.base.BaseFluxUseCase;
import com.pdcmb.covid19stats.domain.usecases.base.IRequest;
import com.pdcmb.covid19stats.domain.usecases.base.IResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Flux;

/**
 * User Case that retrievie data from repository. Output can be filtered by passing filter
 * as a parameter. 
 * 
 * @author Mateusz Ziomek
 */

@Component
public class GetRegionData extends BaseFluxUseCase<GetRegionData.Request, GetRegionData.Response> {

    private final IRegionRepository regionRepository;

    @Autowired
    public GetRegionData(IRegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @Override
    protected Flux<Response> createPublisher(Request request) {

        Flux<Region> stream;

        if(request.getRegionCode() == null)
            stream = regionRepository.getAllRegionData();
        else
            stream = regionRepository.getRegionData(request.getRegionCode());
        return stream
                .flatMap(region -> {
                    return Flux.fromIterable(region.getCountries())
                                .reduce(new ArrayList<Data>(), (data, country) ->{
                                    if(country.getData().size() > data.size())
                                        return country.getData();
                                    else return data;
                                }).flux()
                                .flatMap(data -> {
                                    return Flux.range(0, data.size())
                                                .flatMap( i -> {
                                                    return Flux.fromIterable(region.getCountries())
                                                                .filter(country -> country.getData().size() > i )
                                                                .reduce(new Response(region.getName(), new Data()),
                                                                     (response, country) -> {
                                                                        Data dayData = country.getData().get(i);
                                                                        response.getData().setDate(dayData.getDate());
                                                                        response.getData().add(dayData);
                                                                        return response;
                                                                });
                                                });                    
                                });                                
        });
    }

    /**
     * DTO for input values crossing boundary
     */
    public static class Response implements IResponse{

        private String regionName;
        private Data data;

        public Response(){}

        public Response(String regionName, Data data){
            this.regionName = regionName;
            this.data = data;
        }

        public String getRegionName(){
            return this.regionName;
        }

        public Data getData(){
            return this.data;
        }

    }


    /**
     * DTO for output values crossing boundary
     */
    public static class Request implements IRequest{

        private String regionCode;
        private Filter filter;

        public Request(Filter filter, String regionCode){
            this.filter = filter;
            this.regionCode = regionCode;
        }

        public String getRegionCode(){
            return this.regionCode;
        }

        public Filter getFilter(){
            return this.filter;
        }


    }

}
