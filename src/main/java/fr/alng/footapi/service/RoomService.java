/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.service;

import fr.alng.footapi.dto.RoomDTO;
import fr.alng.footapi.model.Room;

import java.util.List;
import java.util.Optional;

public interface RoomService {
    RoomDTO saveRoom(RoomDTO roomDTO);
    Optional<Room> getRoom(Long id);
    List<Room> getRooms();

    void addMatchToRoom(Long matchApiId, Long roomId);
}
