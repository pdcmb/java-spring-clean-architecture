package com.pdcmb.covid19stats.data.datasources;

import java.util.ArrayList;
import java.util.List;

import com.pdcmb.covid19stats.data.entities.RegionEntity;

import org.springframework.stereotype.Component;

import reactor.core.publisher.Flux;

/**
 * Class that serves as "dummy" cache data store. It stores {@link RegionEntity Region} entities
 * in memory as a simple list. 
 * 
 */
@Component
public class RegionCache {

    private List<RegionEntity> regions = new ArrayList<>();

    /**
     * 
     * @return A {@link Flux} that will all region {@link RegionEntity entities}
     *         stored in cache
     */
    public Flux<RegionEntity> getAll() { 
        return Flux.fromIterable(regions)
                    .map(RegionEntity::new);
    }

    /**
     * Retrieves a specified region.
     * 
     * @param regionCode A string containg region code of region to retrieve
     * @return A {@link Flux} that will emmit requested region {@link RegionEntity entities}
     *         or and empty Flux if no region with given code was found
     */
    public Flux<RegionEntity> get(String regionCode) {
        for (RegionEntity regionEntity : regions) {
            if (regionEntity.getRegionCode().equals(regionCode))
                return Flux.just(regionEntity)
                            .map(RegionEntity::new);
        }
        return Flux.empty();
    }

    /**
     * Adds {@link RegionEntity RegionEntities} passed as arguments
     * to the list of cached regions
     * 
     * @param region Region Entity to add to cache.
     */
    public void add(RegionEntity region) {
        this.regions.add(new RegionEntity(region));
    }
    
}
