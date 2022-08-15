/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.controller;

import fr.alng.footapi.model.Season;
import fr.alng.footapi.service.SeasonService;
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
public class SeasonController {
    private final SeasonService seasonService;

    @GetMapping("/seasons")
    public ResponseEntity<List<Season>> getSeasons(){
        return ResponseEntity.ok().body(seasonService.getSeasons());
    }

    @GetMapping("/season/{id}")
    public ResponseEntity<Optional<Season>> getSeason(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(seasonService.getSeason(id));
    }

    @PostMapping("/season/save")
    public ResponseEntity<Season>saveSeason(@RequestBody Season season){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/season/save").toUriString());
        return ResponseEntity.created(uri).body(seasonService.saveSeason(season));
    }
}
