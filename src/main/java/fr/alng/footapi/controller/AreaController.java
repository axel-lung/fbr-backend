/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.controller;

import fr.alng.footapi.dto.AreaDTO;
import fr.alng.footapi.model.Area;
import fr.alng.footapi.service.AreaService;
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
public class AreaController {
    private final AreaService areaService;

    @GetMapping("/areas")
    public ResponseEntity<List<Area>> getAreas(){
        return ResponseEntity.ok().body(areaService.getAreas());
    }

    @GetMapping("/area/{id}")
    public ResponseEntity<Optional<Area>> getArea(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(areaService.getArea(id));
    }

    @PostMapping("/area/save")
    public ResponseEntity<AreaDTO> saveArea(@RequestBody AreaDTO areaDTO){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/area/save").toUriString());
        return ResponseEntity.created(uri).body(areaService.saveArea(areaDTO));
    }
}
