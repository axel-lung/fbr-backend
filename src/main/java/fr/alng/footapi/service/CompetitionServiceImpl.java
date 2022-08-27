/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.service;

import fr.alng.footapi.converter.ModelConverter;
import fr.alng.footapi.dto.CompetitionDTO;
import fr.alng.footapi.externalapi.CompetitionApi;
import fr.alng.footapi.model.Area;
import fr.alng.footapi.model.Competition;
import fr.alng.footapi.repository.AreaRepository;
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
    private final AreaRepository areaRepository;
    private final ModelConverter<Competition, CompetitionDTO> modelConverter = new ModelConverter<>();

    @Override
    public CompetitionDTO saveCompetition(CompetitionDTO competitionDTO){
        Competition competition = modelConverter.convertDtoToEntity(competitionDTO, Competition.class);
        competition = competitionRepository.save(competition);
        return modelConverter.convertEntityToDto(competition, CompetitionDTO.class);
    }

    @Override
    public Optional<Competition> getCompetition(Long id) {
        return competitionRepository.findById(id);
    }

    @Override
    public List<Competition> getCompetitions() {
        return competitionRepository.findAll();
    }

    @Override
    public Competition getCompetitionByApiId(Long apiId) {
        return competitionRepository.findByApiId(apiId);
    }

    @Override
    public void addAreaToCompetition(Long areaApiId, Long competitionApiId) {
        Area area = areaRepository.findByApiId(areaApiId);
        Competition competition = competitionRepository.findByApiId(competitionApiId);
        competition.setArea(area);
    }

    @Override
    public void updateCompetition(Long apiId, CompetitionApi newCompetition) {
        Competition competition = competitionRepository.findByApiId(apiId);
        competition.setName(newCompetition.getName());
        competition.setCode(newCompetition.getCode());
        competition.setEmblem(newCompetition.getEmblem());
        competition.setType(newCompetition.getType());
        competitionRepository.save(competition);
    }
}
