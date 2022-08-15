/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.service;

import fr.alng.footapi.model.Competition;

import java.util.List;
import java.util.Optional;

public interface CompetitionService {

    Competition saveCompetition(Competition competition);
    Optional<Competition> getCompetition(Long id);
    List<Competition> getCompetitions();
}
