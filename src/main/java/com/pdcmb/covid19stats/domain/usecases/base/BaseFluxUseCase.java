package com.pdcmb.covid19stats.domain.usecases.base;

import org.reactivestreams.Publisher;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * Abstract class rappresenting base asynchronous Use Case (Interactor)
 * which returns a {@link Flux} 
 *
 * @author Mateusz Ziomek
 * @param <I> Parameter (input) consumed by Use Case
 * @param <O> Value emitted (output) by the returned stream
 * @see BaseMonoUseCase
 */
public abstract class BaseFluxUseCase<I, O> {

    /**
     * Creates {@link Publisher} used by {@link #execute} method
     *
     * @param request Parameters passed to and used by use case
     * @return {@link Flux} which emits requested value
     */
    protected abstract Flux<O> createPublisher(I request);

    /**
     * Executes Use Case and returns {@link Flux} which emitts values
     *
     * @param params Parameter to be passed to Use Case
     * @return {@link Flux} which emits requested values
     */
    public Flux<O> execute(I request){
        return createPublisher(request)
                    .subscribeOn(Schedulers.elastic());
    }

}
