/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.service;


import fr.alng.footapi.model.Room;
import fr.alng.footapi.repository.RoomRepository;

import java.util.List;
import java.util.Optional;

public class RoomServiceImpl implements RoomService{

    private RoomRepository roomRepository;

    @Override
    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Optional<Room> getRoom(Long id) {
        return roomRepository.findById(id);
    }

    @Override
    public List<Room> getRooms() {
        return roomRepository.findAll();
    }
}
