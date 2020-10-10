package com.pdcmb.covid19stats.domain.usecase.base;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import reactor.core.Disposable;
import reactor.core.Disposables;
import reactor.core.Disposable.Composite;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import reactor.function.Consumer;

/**
 * Classe astratta di un Use Case (o Interactor) e rappressenta un'unità di caso
 * d'uso
 *
 *
 * Use Case di questo tipo esegue l'operazione in modo asincrono (in background)
 * e pubblichera il risultato attraverso [DisposableSingleObserver] passato come
 * parametro. Il valore di ritorno è emesso tramite [Single]
 *
 * @param <P> Il parametro (input) d'ingresso
 * @param <R> Il valore di ritorno (output)
 */
public abstract class BaseMonoUseCase<P, R> {

    /**
     * Creates [Publisher] used by execute method
     *
     * @param params
     * @return
     */
    protected abstract Mono<R> createPublisher(P params);

    /**
     * Executes Use Case and subscribe with given [Subscriber] 
     *
     * @param subscriber
     * @param params Parameter to be passed to Use Case
     */
    public Mono<R> execute(P params){
        return createPublisher(params)
                .subscribeOn(Schedulers.elastic());
    }



}
