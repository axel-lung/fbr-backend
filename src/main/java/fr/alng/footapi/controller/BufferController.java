/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.controller;

import fr.alng.footapi.dto.BufferDTO;
import fr.alng.footapi.model.Buffer;
import fr.alng.footapi.service.BufferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BufferController {

    private final BufferService bufferService;

    @PostMapping("/buffer/save")
    public ResponseEntity<BufferDTO> saveBuffer(@RequestBody BufferDTO bufferDTO){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/buffer/save").toUriString());
        return ResponseEntity.created(uri).body(bufferService.saveBuffer(bufferDTO));
    }

    @GetMapping("/buffer/type")
    public ResponseEntity<List<Buffer>> findByType(@RequestParam String type, @RequestParam boolean isExecuted){
        return ResponseEntity.ok().body(bufferService.findByType(type, isExecuted));
    }
}
