/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.service;

import fr.alng.footapi.model.Area;
import fr.alng.footapi.model.Team;

import java.util.List;
import java.util.Optional;

public interface TeamService {
    Team saveTeam(Team team);
    Optional<Team> getTeam(Long id);
    List<Team> geTeams();
    Team getTeamByApiId(Long apiId);
}
