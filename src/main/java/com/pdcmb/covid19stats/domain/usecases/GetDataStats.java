package com.pdcmb.covid19stats.domain.usecases;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import com.pdcmb.covid19stats.domain.entities.Data;
import com.pdcmb.covid19stats.domain.entities.Region;
import com.pdcmb.covid19stats.domain.entities.Stats;
import com.pdcmb.covid19stats.domain.usecases.base.BaseMonoUseCase;
import com.pdcmb.covid19stats.domain.usecases.base.IRequest;
import com.pdcmb.covid19stats.domain.usecases.base.IResponse;

import org.springframework.stereotype.Component;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class GetDataStats extends BaseMonoUseCase<GetDataStats.Request, GetDataStats.Response> {

    @Override
    protected Mono<Response> createPublisher(Request request) {
        try {

            Method getter = Data.class.getMethod(
                    "get" + request.getField().substring(0, 1).toUpperCase() + request.getField().substring(1));

            return Flux.fromIterable(request.getRegions())
                    .flatMap(region -> Flux.fromIterable(region.getData()))
                    .reduce(new Stats(request.getField(), 0, 0, 0, 0, 0), 
                        (stats, data) -> {

                        Integer value;

                        try {

                            value = (Integer) getter.invoke(data);
                            return stats.count(stats.getCount() + 1)
                                .min(stats.getMin() > value ? value : stats.getMin())
                                .max(stats.getMax() < value ? value : stats.getMin())
                                .sum(stats.getSum() + value)
                                .average(stats.getSum() / stats.getCount());

                        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                            throw new RuntimeException(e);
                        }
  
                    })
                    .map(Response::new);

        } catch (IllegalArgumentException | SecurityException ex) {
            return Mono.error(ex);
        } catch (NoSuchMethodException ex){
            throw new NoSuchFieldError("Field " + request.getField() + " doesn't exist");
        }
     
    }

 
    public static class Request implements IRequest{

        private List<Region> regions;
        private String field;

        public Request(List<Region> regions, String field){
            this.regions = regions;
            this.field = field;
        }

        public List<Region> getRegions(){
            return this.regions;
        }

        public String getField(){
            return this.field;
        }
    }

    public static class Response implements IResponse{

        private Stats stats;

        public Response(){}

        public Response(Stats stats){
            this.stats = stats;
        }

        public Stats getStats(){
            return this.stats;
        }

    }

}
