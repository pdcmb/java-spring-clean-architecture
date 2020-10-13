package com.pdcmb.covid19stats.data.datasources;

import com.pdcmb.covid19stats.data.datasources.dto.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
public class Covid19Api {

    private final WebClient webClient;

    @Autowired
    public Covid19Api(WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<ResponseEntity> getCountryData(String country) {
        return webClient
                .get()
                .uri("/dayone/country/" + country)
                .retrieve()
                .bodyToFlux(ResponseEntity.class);
    }
    
}
