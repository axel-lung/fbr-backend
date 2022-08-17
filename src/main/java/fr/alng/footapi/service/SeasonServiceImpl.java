/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.service;

import fr.alng.footapi.model.Area;
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

    @Override
    public Season saveSeason(Season season) {
        return seasonRepository.save(season);
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
