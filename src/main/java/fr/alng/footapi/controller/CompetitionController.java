/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.controller;

import fr.alng.footapi.model.Competition;
import fr.alng.footapi.service.CompetitionService;
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
public class CompetitionController {
    private final CompetitionService competitionService;

    @GetMapping("/competitions")
    public ResponseEntity<List<Competition>>getCompetitions(){
        return ResponseEntity.ok().body(competitionService.getCompetitions());
    }

    @GetMapping("/competition/{id}")
    public ResponseEntity<Optional<Competition>> getCompetition(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(competitionService.getCompetition(id));
    }

    @PostMapping("/competition/save")
    public ResponseEntity<Competition>saveCompetition(@RequestBody Competition competition){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/competition/save").toUriString());
        return ResponseEntity.created(uri).body(competitionService.saveCompetition(competition));
    }
}
