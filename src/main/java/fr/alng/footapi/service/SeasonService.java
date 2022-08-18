/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.service;

import fr.alng.footapi.dto.SeasonDTO;
import fr.alng.footapi.model.Season;

import java.util.List;
import java.util.Optional;

public interface SeasonService {
    SeasonDTO saveSeason(SeasonDTO seasonDTO);
    Optional<Season> getSeason(Long id);
    List<Season> getSeasons();
    Season getSeasonByApiId(Long apiId);
}
