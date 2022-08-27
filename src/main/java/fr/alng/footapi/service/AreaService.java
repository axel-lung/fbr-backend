/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.service;

import fr.alng.footapi.dto.AreaDTO;
import fr.alng.footapi.externalapi.AreaApi;
import fr.alng.footapi.model.Area;

import java.util.List;
import java.util.Optional;

public interface AreaService {
    AreaDTO saveArea(AreaDTO areaDTO);
    Optional<Area> getArea(Long id);
    List<Area> getAreas();
    Area getAreaByApiId(Long apiId);
    void updateArea(Long apiId, AreaApi newArea);
}
