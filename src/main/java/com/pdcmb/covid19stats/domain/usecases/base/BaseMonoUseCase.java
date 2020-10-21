package com.pdcmb.covid19stats.domain.usecases.base;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * Classe astratta di un Use Case (o Interactor) e rappressenta un'unità di caso
 * d'uso
 *
 *
 * Use Case di questo tipo esegue l'operazione in modo asincrono (in background)
 * e pubblichera il risultato attraverso [DisposableSingleObserver] passato come
 * parametro. Il valore di ritorno è emesso tramite [Single]
 *
 * @param <I> Il parametro (input) d'ingresso
 * @param <O> Il valore di ritorno (output)
 * @see BaseFluxUseCase
 */
public abstract class BaseMonoUseCase<I extends IRequest, O extends IResponse> {

    /**
     * Creates the {@link Mono} used by execute method
     *
     * @param params Parameters passed to and used by use case
     * @return {@link Mono} which emitt requested value
     */
    protected abstract Mono<O> createPublisher(I params);

    /**
     * Executes Use Case and returns {@link Mono} which emmitts a value.
     *
     * @param subscriber
     * @param params Parameter to be passed to Use Case
     */
    public Mono<O> execute(I params){
        return createPublisher(params)
                    .subscribeOn(Schedulers.elastic());
    }



}
