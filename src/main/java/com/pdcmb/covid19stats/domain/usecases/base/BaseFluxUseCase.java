package com.pdcmb.covid19stats.domain.usecases.base;

import org.reactivestreams.Publisher;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * Abstract class rappresenting base synchronous Use Case (Interactor) that 
 *
 *
 * @author Mateusz Ziomek
 * @param <I> Parameter (input) consumed by Use Case
 * @param <O> Value emitted (output) by the returned stream
 */
public abstract class BaseFluxUseCase<I, O> {

    /**
     * Creates {@link Publisher} used by {@link #execute} method
     *
     * @param params
     * @return
     */
    protected abstract Flux<O> createPublisher(I params);

    /**
     * Executes Use Case and returns {@link Flux} which emitts values
     *
     * @param params Parameter to be passed to Use Case
     */
    public Flux<O> execute(I params){
        return createPublisher(params)
                    .subscribeOn(Schedulers.elastic());
    }

}
