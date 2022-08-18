/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.controller;

import fr.alng.footapi.dto.MatchDTO;
import fr.alng.footapi.model.Match;
import fr.alng.footapi.service.MatchService;
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
public class MatchController {

    private MatchService matchService;

    @GetMapping("/matches")
    public ResponseEntity<List<Match>> getMatches(){
        return ResponseEntity.ok().body(matchService.getMatches());
    }

    @GetMapping("/match/{id}")
    public ResponseEntity<Optional<Match>> getCompetition(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(matchService.getMatch(id));
    }

    @PostMapping("/match/save")
    public ResponseEntity<MatchDTO>saveCompetition(@RequestBody MatchDTO matchDTO){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/match/save").toUriString());
        return ResponseEntity.created(uri).body(matchService.saveMatch(matchDTO));
    }
}
