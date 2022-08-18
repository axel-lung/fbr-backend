/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.service;


import fr.alng.footapi.converter.ModelConverter;
import fr.alng.footapi.dto.RoomDTO;
import fr.alng.footapi.model.Room;
import fr.alng.footapi.repository.RoomRepository;

import java.util.List;
import java.util.Optional;

public class RoomServiceImpl implements RoomService{

    private RoomRepository roomRepository;
    private final ModelConverter<Room, RoomDTO> modelConverter = new ModelConverter<>();

    @Override
    public RoomDTO saveRoom(RoomDTO roomDTO) {
        Room room = modelConverter.convertDtoToEntity(roomDTO, Room.class);
        room = roomRepository.save(room);
        return modelConverter.convertEntityToDto(room, RoomDTO.class);
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
