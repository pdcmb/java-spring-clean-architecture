package com.pdcmb.covid19stats.domain.usecases;

import com.pdcmb.covid19stats.domain.entities.Data;
import com.pdcmb.covid19stats.domain.entities.Filter;
import com.pdcmb.covid19stats.domain.repositories.IDataRepository;
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
public class GetData extends BaseFluxUseCase<GetData.Request, GetData.Response> {

    private final IDataRepository dataRepository;

    @Autowired
    public GetData(IDataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }


    @Override
    protected Flux<Response> createPublisher(Request request) {

        Flux<Data> stream;

        if(request.getRegionCode() == null){
            stream = dataRepository.getAllData();
        } else{
            stream = dataRepository.getDataByRegion(request.getRegionCode());
        }   
        if (request.getFilters() != null && request.getFilters().length != 0) {
            stream = stream.filterWhen(data -> 
                                Flux.fromArray(request.getFilters())
                                    .all(filter -> filter.apply(data))
                            );
        }
        return stream.map(Response::new);
    }

    /**
     * DTO for input values crossing boundary
     */
    public static class Response implements IResponse{

        private Data data;

        public Response(){}

        public Response(Data data){
            this.data = data;
        }

        public Data getRegion(){
            return this.data;
        }

    }


    /**
     * DTO for output values crossing boundary
     */
    public static class Request implements IRequest{

        private String regionCode;
        private Filter[] filters;

        public Request(){}

        public Request(String regionCode, Filter... filters){
            this.regionCode = regionCode;
            this.filters = filters;
        }

        public String getRegionCode(){
            return this.regionCode;
        }

        public Filter[] getFilters(){
            return this.filters;
        }


    }

}
