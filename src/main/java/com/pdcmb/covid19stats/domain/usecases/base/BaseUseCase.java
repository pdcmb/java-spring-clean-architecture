package com.pdcmb.covid19stats.domain.usecases.base;

/**
 * Base interface for an Synchronous Use Case (or Interactor)
 *
 * This interface is used for synchronous requests that doesn't require
 * multithreading. {@link #execute} method return value directly.
 *
 * @author Mateusz Ziomek
 * @param <P> Parameter (input) consumed by Use Case
 * @param <R> Value returned by Use Case (output)
 */
public interface BaseUseCase<I extends IRequest, O extends IResponse>{
    
    /**
     * Executes Use Case and returns value
     *
     * @param params Parameter to be passed to Use Case
     * @return Value retuned by Use Case
     */
    O execute(I params);

}