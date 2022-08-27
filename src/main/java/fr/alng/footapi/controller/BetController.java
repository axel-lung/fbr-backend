/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.controller;

import fr.alng.footapi.dto.BetDTO;
import fr.alng.footapi.model.Bet;
import fr.alng.footapi.service.BetService;
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
public class BetController {

    private final BetService betService;

    @GetMapping("/bets")
    public ResponseEntity<List<Bet>> getBets(){
        return ResponseEntity.ok().body(betService.getBets());
    }

    @GetMapping("/bet/{id}")
    public ResponseEntity<Optional<Bet>> getBet(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(betService.getBet(id));
    }

    @PostMapping("/bet/save")
    public ResponseEntity<BetDTO>saveBet(@RequestBody BetDTO betDTO){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/bet/save").toUriString());
        return ResponseEntity.created(uri).body(betService.saveBet(betDTO));
    }

}
