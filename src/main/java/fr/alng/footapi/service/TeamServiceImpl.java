/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.service;


import fr.alng.footapi.converter.ModelConverter;
import fr.alng.footapi.dto.TeamDTO;
import fr.alng.footapi.externalapi.TeamApi;
import fr.alng.footapi.model.Team;
import fr.alng.footapi.repository.TeamRepository;
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
public class TeamServiceImpl implements TeamService{

    private final TeamRepository teamRepository;
    private final ModelConverter<Team, TeamDTO> modelConverter = new ModelConverter<>();

    @Override
    public TeamDTO saveTeam(TeamDTO teamDTO) {
        Team team = modelConverter.convertDtoToEntity(teamDTO, Team.class);
        team = teamRepository.save(team);
        return modelConverter.convertEntityToDto(team, TeamDTO.class);
    }

    @Override
    public Optional<Team> getTeam(Long id) {
        return teamRepository.findById(id);
    }

    @Override
    public List<Team> geTeams() {
        return teamRepository.findAll();
    }

    @Override
    public Team getTeamByApiId(Long apiId) {
        return teamRepository.findByApiId(apiId);
    }

    @Override
    public void updateTeam(Long apiId, TeamApi newTeam) {
        Team team = new Team();
        team.setName(newTeam.getName());
        team.setShortName(newTeam.getShortName());
        team.setCrest(newTeam.getCrest());
        team.setTla(newTeam.getTla());
        teamRepository.save(team);
    }
}
