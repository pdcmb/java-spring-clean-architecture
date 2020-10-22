package com.pdcmb.covid19stats.data.entities.mappers;

import java.util.stream.Collectors;

import com.pdcmb.covid19stats.data.entities.RegionEntity;
import com.pdcmb.covid19stats.domain.entities.Region;

import org.springframework.stereotype.Component;

/**
 * Maps {@link RegionEntity} instance to domain's
 * {@link Region} instance
 * 
 * @author Mateusz Ziomek
 */
@Component
public class RegionEntityToRegion {
    
    private DataEntityToData dataMapper;

    public RegionEntityToRegion(DataEntityToData dataMapper){
        this.dataMapper = dataMapper;
    }

    /**
     * Transforms {@link RegionEntity} into a {@link Region}
     * 
     * @param regionEntity Region data entity to be transformed
     * @return A corresponding domain Region entity
     */
    public Region map(RegionEntity regionEntity){
        Region region = new Region(
                            regionEntity.getRegionCode(),
                            regionEntity.getRegionName(),
                            null);
        return region.data(regionEntity.getData().stream()
                        .map(dataEntity -> dataMapper.map(dataEntity, region))
                        .collect(Collectors.toList()));
    }
}
