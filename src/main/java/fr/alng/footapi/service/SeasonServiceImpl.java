/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.service;

import fr.alng.footapi.converter.ModelConverter;
import fr.alng.footapi.dto.SeasonDTO;
import fr.alng.footapi.model.Season;
import fr.alng.footapi.repository.SeasonRepository;
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
public class SeasonServiceImpl implements SeasonService{

    private final SeasonRepository seasonRepository;
    private final ModelConverter<Season, SeasonDTO> modelConverter = new ModelConverter<>();

    @Override
    public SeasonDTO saveSeason(SeasonDTO seasonDTO) {
        Season season = modelConverter.convertDtoToEntity(seasonDTO, Season.class);
        season = seasonRepository.save(season);
        return modelConverter.convertEntityToDto(season, SeasonDTO.class);
    }

    @Override
    public Optional<Season> getSeason(Long id) {
        return seasonRepository.findById(id);
    }

    @Override
    public List<Season> getSeasons() {
        return seasonRepository.findAll();
    }

    @Override
    public Season getSeasonByApiId(Long apiId) {
        return seasonRepository.findByApiId(apiId);
    }
}
