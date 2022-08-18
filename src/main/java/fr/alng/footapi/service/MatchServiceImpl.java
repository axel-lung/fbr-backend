/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.service;

import fr.alng.footapi.converter.ModelConverter;
import fr.alng.footapi.dto.MatchDTO;
import fr.alng.footapi.model.Match;
import fr.alng.footapi.repository.MatchRepository;

import java.util.List;
import java.util.Optional;

public class MatchServiceImpl implements MatchService{

    private MatchRepository matchRepository;
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
}
