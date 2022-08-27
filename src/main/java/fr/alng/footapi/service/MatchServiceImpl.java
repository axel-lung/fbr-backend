/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.service;

import fr.alng.footapi.converter.ModelConverter;
import fr.alng.footapi.dto.MatchDTO;
import fr.alng.footapi.externalapi.MatchApi;
import fr.alng.footapi.model.Area;
import fr.alng.footapi.model.Competition;
import fr.alng.footapi.model.Match;
import fr.alng.footapi.model.Team;
import fr.alng.footapi.repository.AreaRepository;
import fr.alng.footapi.repository.CompetitionRepository;
import fr.alng.footapi.repository.MatchRepository;
import fr.alng.footapi.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MatchServiceImpl implements MatchService{

    private final MatchRepository matchRepository;
    private final AreaRepository areaRepository;
    private final CompetitionRepository competitionRepository;
    private final TeamRepository teamRepository;
    private final ModelConverter<Match, MatchDTO> modelConverter = new ModelConverter<>();

    @Override
    public MatchDTO saveMatch(MatchDTO matchDTO) {
        Match match = modelConverter.convertDtoToEntity(matchDTO, Match.class);
        match = matchRepository.save(match);
        return modelConverter.convertEntityToDto(match, MatchDTO.class);
    }

    @Override
    public Optional<Match> getMatch(Long id) {
        return matchRepository.findById(id);
    }

    @Override
    public List<Match> getMatches() {
        return matchRepository.findAll();
    }

    @Override
    public Match getMatchByApiId(Long apiId) {
        return matchRepository.findByApiId(apiId);
    }

    @Override
    public void addAreaToMatch(Long areaApiId, Long matchApiId) {
        Area area = areaRepository.findByApiId(areaApiId);
        Match match = matchRepository.findByApiId(matchApiId);
        match.setArea(area);
    }

    @Override
    public void addCompetitionToMatch(Long competitionApiId, Long matchApiId) {
        Competition competition = competitionRepository.findByApiId(competitionApiId);
        Match match = matchRepository.findByApiId(matchApiId);
        match.setCompetition(competition);
    }

    @Override
    public void addHomeTeamToMatch(Long teamApiId, Long matchApiId) {
        Team team = teamRepository.findByApiId(teamApiId);
        Match match = matchRepository.findByApiId(matchApiId);
        match.setHomeTeam(team);
    }

    @Override
    public void addAwayTeamToMatch(Long teamApiId, Long matchApiId) {
        Team team = teamRepository.findByApiId(teamApiId);
        Match match = matchRepository.findByApiId(matchApiId);
        match.setAwayTeam(team);
    }

    @Override
    public void addWinnerTeamToMatch(Long teamApiId, Long matchApiId) {
        Team team = teamRepository.findByApiId(teamApiId);
        Match match = matchRepository.findByApiId(matchApiId);
        match.setWinnerTeam(team);
    }

    @Override
    public void updateMatch(Long apiId, MatchApi newMatch) {
        Match match = matchRepository.findByApiId(apiId);
        match.setUtcDate(newMatch.getUtcDate());
        match.setStatus(newMatch.getStatus().toString());
        match.setStage(newMatch.getStage().toString());
        match.setMatchday(newMatch.getMatchday());
        match.setGroupe(newMatch.getGroup());
        match.setDuration(newMatch.getScore().getDuration());
        match.setHomeScore(newMatch.getScore().getFullTime().getHome());
        match.setAwayScore(newMatch.getScore().getFullTime().getAway());
        matchRepository.save(match);
    }

    @Override
    public List<Match> findMatchesBetweenDates(Date dateFrom, Date dateTo) {
        return matchRepository.findMatchesBetweenDates(dateFrom, dateTo);
    }
}
