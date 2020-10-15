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
 */
public abstract class BaseMonoUseCase<I extends IRequest, O extends IResponse> {

    /**
     * Creates [Publisher] used by execute method
     *
     * @param params
     * @return
     */
    protected abstract Mono<O> createPublisher(I params);

    /**
     * Executes Use Case and subscribe with given [Subscriber] 
     *
     * @param subscriber
     * @param params Parameter to be passed to Use Case
     */
    public Mono<O> execute(I params){
        return createPublisher(params)
                    .subscribeOn(Schedulers.elastic());
    }



}
