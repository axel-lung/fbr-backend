/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.service;

import fr.alng.footapi.dto.CompetitionDTO;
import fr.alng.footapi.model.Competition;

import java.util.List;
import java.util.Optional;

public interface CompetitionService {

    CompetitionDTO saveCompetition(CompetitionDTO competitionDTO);
    Optional<Competition> getCompetition(Long id);
    List<Competition> getCompetitions();
    Competition getCompetitionByApiId(Long apiId);

}
