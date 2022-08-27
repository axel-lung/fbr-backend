/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.controller;

import fr.alng.footapi.dto.TeamDTO;
import fr.alng.footapi.model.Team;
import fr.alng.footapi.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;

    @GetMapping("/teams")
    public ResponseEntity<List<Team>> getTeams(){
        return ResponseEntity.ok().body(teamService.geTeams());
    }

    @GetMapping("/team/{id}")
    public ResponseEntity<Optional<Team>> getCompetition(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(teamService.getTeam(id));
    }

    @PostMapping("/team/save")
    public ResponseEntity<TeamDTO>saveCompetition(@RequestBody TeamDTO teamDTO){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/team/save").toUriString());
        return ResponseEntity.created(uri).body(teamService.saveTeam(teamDTO));
    }

}
