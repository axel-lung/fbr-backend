/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.controller;

import fr.alng.footapi.dto.RoomDTO;
import fr.alng.footapi.model.Room;
import fr.alng.footapi.service.RoomService;
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
public class RoomController {
    private RoomService roomService;

    @GetMapping("/rooms")
    public ResponseEntity<List<Room>> getRooms(){
        return ResponseEntity.ok().body(roomService.getRooms());
    }

    @GetMapping("/room/{id}")
    public ResponseEntity<Optional<Room>> getCompetition(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(roomService.getRoom(id));
    }

    @PostMapping("/room/save")
    public ResponseEntity<RoomDTO>saveCompetition(@RequestBody RoomDTO roomDTO){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/room/save").toUriString());
        return ResponseEntity.created(uri).body(roomService.saveRoom(roomDTO));
    }
}
