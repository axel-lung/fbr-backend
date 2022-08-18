/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.service;


import fr.alng.footapi.converter.ModelConverter;
import fr.alng.footapi.dto.TeamDTO;
import fr.alng.footapi.model.Team;
import fr.alng.footapi.repository.TeamRepository;

import java.util.List;
import java.util.Optional;

public class TeamServiceImpl implements TeamService{

    private TeamRepository teamRepository;
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
}
