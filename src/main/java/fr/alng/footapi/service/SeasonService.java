/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.service;

import fr.alng.footapi.model.Season;

import java.util.List;
import java.util.Optional;

public interface SeasonService {
    Season saveSeason(Season season);
    Optional<Season> getSeason(Long id);
    List<Season> getSeasons();
    Season getSeasonByApiId(Long apiId);
}
