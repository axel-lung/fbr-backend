/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.service;

import fr.alng.footapi.dto.MatchDTO;
import fr.alng.footapi.externalapi.MatchApi;
import fr.alng.footapi.model.Match;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MatchService {
    MatchDTO saveMatch(MatchDTO matchDTO);
    Optional<Match> getMatch(Long id);
    List<Match> getMatches();
    Match getMatchByApiId(Long apiId);
    void addAreaToMatch(Long areaApiId, Long matchApiId);
    void addCompetitionToMatch(Long competitionApiId, Long matchApiId);
    void addHomeTeamToMatch(Long teamApiId, Long matchApiId);
    void addAwayTeamToMatch(Long teamApiId, Long matchApiId);
    void addWinnerTeamToMatch(Long teamApiId, Long matchApiId);
    void updateMatch(Long apiId, MatchApi newMatch);

    List<Match> findMatchesBetweenDates(Date dateFrom, Date dateTo);
}
