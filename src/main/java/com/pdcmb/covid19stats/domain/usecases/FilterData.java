package com.pdcmb.covid19stats.domain.usecases;

import com.pdcmb.covid19stats.domain.entities.Filter;
import com.pdcmb.covid19stats.domain.entities.Region;
import com.pdcmb.covid19stats.domain.usecases.base.BaseMonoUseCase;
import com.pdcmb.covid19stats.domain.usecases.base.IRequest;
import com.pdcmb.covid19stats.domain.usecases.base.IResponse;

import org.springframework.stereotype.Component;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Filters Region dataset using provided {@link Filter}
 * 
 * @author Mateusz Ziomek
 */
@Component
public class FilterData extends BaseMonoUseCase<FilterData.Request, FilterData.Response> {

    @Override
    protected Mono<Response> createPublisher(Request request) {

        if (request.getFilters() != null && request.getFilters().length != 0) {
            return Mono.just(request.getRegion())
                        .flatMap(region ->{ 
                            return Flux.fromIterable(region.getData())
                                        .filterWhen(data -> {
                                            return Flux.fromArray(request.getFilters())
                                                        .all(filter -> filter.apply(data));
                                        })
                                        .collectList()
                                        .map(dataList -> region.data(dataList));
                        })
                        .map(Response::new); 

        }
        return Mono.just(request.getRegion())
                    .map(Response::new); 
    }


    public static class Request implements IRequest {

        private Region region;

        private Filter[] filters;

        public Request(Region region, Filter[] filters){
            this.region = region;
            this.filters = filters;
        }

        public Region getRegion(){
            return this.region;
        }

        public Filter[] getFilters(){
            return this.filters;
        }

    }

    public static class Response implements IResponse {

        private Region region;

        public Response(Region region){
            this.region = region;
        }

        public Region getRegion(){
            return this.region;
        }
    }

    
}
