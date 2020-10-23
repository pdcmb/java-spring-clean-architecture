package com.pdcmb.covid19stats.domain.usecases;

import com.pdcmb.covid19stats.domain.entities.Region;
import com.pdcmb.covid19stats.domain.repositories.IRegionRepository;
import com.pdcmb.covid19stats.domain.usecases.base.BaseFluxUseCase;
import com.pdcmb.covid19stats.domain.usecases.base.IRequest;
import com.pdcmb.covid19stats.domain.usecases.base.IResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Flux;

/**
 * User Case that retrievie data about specific region/s from repository.
 * Region can be speOutput can be filtered by passing filter
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

        if(request.getRegionCode() == null){
            stream = regionRepository.getAllRegions();
        } else{
            stream = regionRepository.getRegionbyCode(request.getRegionCode());
        }   

        return stream.map(Response::new);
    }

    /**
     * DTO for input values crossing boundary
     */
    public static class Response implements IResponse{

        private Region region;

        public Response(){}

        public Response(Region region){
            this.region = region;
        }

        public Region getRegion(){
            return this.region;
        }

    }


    /**
     * Class that rappresents a request
     */
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

}
