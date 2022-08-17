/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.service;


import fr.alng.footapi.model.Team;
import fr.alng.footapi.repository.TeamRepository;

import java.util.List;
import java.util.Optional;

public class TeamServiceImpl implements TeamService{

    private TeamRepository teamRepository;

    @Override
    public Team saveTeam(Team team) {
        return teamRepository.save(team);
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
