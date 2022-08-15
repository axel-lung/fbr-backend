/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.service;

import fr.alng.footapi.model.Competition;
import fr.alng.footapi.repository.CompetitionRepository;
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
public class CompetitionServiceImpl implements CompetitionService {
    private final CompetitionRepository competitionRepository;

    @Override
    public Competition saveCompetition(Competition competition){
        return competitionRepository.save(competition);
    }

    @Override
    public Optional<Competition> getCompetition(Long id) {
        return competitionRepository.findById(id);
    }

    @Override
    public List<Competition> getCompetitions() {
        return competitionRepository.findAll();
    }
}
