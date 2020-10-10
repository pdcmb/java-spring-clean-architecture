package com.pdcmb.covid19stats.domain.usecase;

import java.time.Period;
import java.util.Objects;

import com.pdcmb.covid19stats.domain.Region;
import com.pdcmb.covid19stats.domain.repositories.IRegionRepository;
import com.pdcmb.covid19stats.domain.usecase.base.BaseFluxUseCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Flux;

/**
 * 
 * 
 * @author Mateusz Ziomek
 */
@Component
public class GetRegionData extends BaseFluxUseCase<String, Region> {

    private final IRegionRepository regionRepository;


    @Autowired
    public GetRegionData(IRegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @Override
    protected Flux<Region> createPublisher(String params) {
        return regionRepository.getRegionData("eu");

    }

}
