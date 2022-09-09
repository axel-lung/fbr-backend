/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.service;


import fr.alng.footapi.converter.ModelConverter;
import fr.alng.footapi.dto.RoomDTO;
import fr.alng.footapi.model.Match;
import fr.alng.footapi.model.Room;
import fr.alng.footapi.model.User;
import fr.alng.footapi.repository.MatchRepository;
import fr.alng.footapi.repository.RoomRepository;
import fr.alng.footapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RoomServiceImpl implements RoomService{

    private final RoomRepository roomRepository;
    private final MatchRepository matchRepository;
    private final UserRepository userRepository;
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

    @Override
    public void addMatchToRoom(Long matchApiId, Long roomId) {
        Match match = matchRepository.findByApiId(matchApiId);
        Room room = roomRepository.getReferenceById(roomId);
        log.info("match " + match.getId());
        log.info("room" + room.getId());

        boolean bool = room.getMatches().add(match);
        if(bool) log.info("true");
    }

    @Override
    public void addUserToRoom(Long userId, Long roomId) {
        User user = userRepository.getReferenceById(userId);
        Room room = roomRepository.getReferenceById(roomId);
        room.getUsers().add(user);
    }
}
