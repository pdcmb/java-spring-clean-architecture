package com.pdcmb.covid19stats.domain.usecase.base;

/**
 *Base interface for an Use Case (or Interactor)
 *
 * This interface is used for synchronous requests that doesn't require
 * multithreading. Doesn't need to be disposed.
 *
 * @author Mateusz Ziomek
 * @param <P> Parameter (input) consumed by Use Case
 * @param <R> Value returned by Use Case (output)
 */
public interface BaseSynchronousUseCase<P, R> {
    
    /**
     * Executes Use Case and returns value
     *
     * @param params Parameter to be passed to Use Case
     * @return Value retuned by Use Case
     */
    R execute(P params);

}