/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.service;

import fr.alng.footapi.model.Match;
import fr.alng.footapi.repository.MatchRepository;

import java.util.List;
import java.util.Optional;

public class MatchServiceImpl implements MatchService{

    private MatchRepository matchRepository;

    @Override
    public Match saveMatch(Match match) {
        return matchRepository.save(match);
    }

    @Override
    public Optional<Match> getMatch(Long id) {
        return matchRepository.findById(id);
    }

    @Override
    public List<Match> getMatches() {
        return matchRepository.findAll();
    }
}
