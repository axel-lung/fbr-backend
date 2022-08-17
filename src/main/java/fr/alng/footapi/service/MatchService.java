/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.service;

import fr.alng.footapi.model.Match;

import java.util.List;
import java.util.Optional;

public interface MatchService {
    Match saveMatch(Match match);
    Optional<Match> getMatch(Long id);
    List<Match> getMatches();
    Match getMatchByApiId(Long apiId);

}
