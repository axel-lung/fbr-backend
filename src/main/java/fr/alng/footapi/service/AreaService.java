/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.service;

import fr.alng.footapi.model.Area;

import java.util.List;
import java.util.Optional;

public interface AreaService {
    Area saveArea(Area area);
    Optional<Area> getArea(Long id);
    List<Area> getAreas();
}
