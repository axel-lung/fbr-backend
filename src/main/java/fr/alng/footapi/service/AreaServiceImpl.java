/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.service;

import fr.alng.footapi.converter.ModelConverter;
import fr.alng.footapi.dto.AreaDTO;
import fr.alng.footapi.externalapi.AreaApi;
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
public class AreaServiceImpl implements AreaService{

    private final AreaRepository areaRepository;
    private final ModelConverter<Area, AreaDTO> modelConverter = new ModelConverter<>();

    @Override
    public AreaDTO saveArea(AreaDTO areaDTO) {
        Area area = modelConverter.convertDtoToEntity(areaDTO, Area.class);
        area = areaRepository.save(area);
        return modelConverter.convertEntityToDto(area, AreaDTO.class);
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

    @Override
    public void updateArea(Long apiId, AreaApi newArea) {
        Area area = areaRepository.findByApiId(apiId);
        area.setName(newArea.getName());
        area.setCountryCode(newArea.getCode());
        area.setFlag(newArea.getFlag());
        areaRepository.save(area);
    }
}
