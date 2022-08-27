/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.service;

import fr.alng.footapi.dto.TeamDTO;
import fr.alng.footapi.externalapi.TeamApi;
import fr.alng.footapi.model.Team;

import java.util.List;
import java.util.Optional;

public interface TeamService {
    TeamDTO saveTeam(TeamDTO teamDTO);
    Optional<Team> getTeam(Long id);
    List<Team> geTeams();
    Team getTeamByApiId(Long apiId);
    void updateTeam(Long apiId, TeamApi newTeam);
}
