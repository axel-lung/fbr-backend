/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.service;

import fr.alng.footapi.model.Area;
import fr.alng.footapi.repository.AreaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AreaServiceImpl implements  AreaService{

    private final AreaRepository areaRepository;

    @Override
    public Area saveArea(Area area) {
        return areaRepository.save(area);
    }

    @Override
    public Optional<Area> getArea(Long id) {
        return areaRepository.findById(id);
    }

    @Override
    public List<Area> getAreas() {
        return areaRepository.findAll();
    }

    @Override
    public Area getAreaByApiId(Long apiId) {
        return areaRepository.findByApiId(apiId);
    }
}
