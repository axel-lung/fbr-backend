/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.externalapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fr.alng.footapi.converter.ModelConverter;
import fr.alng.footapi.dto.RoomDTO;
import fr.alng.footapi.model.Match;
import fr.alng.footapi.model.Room;
import fr.alng.footapi.repository.MatchRepository;
import fr.alng.footapi.repository.RoomRepository;
import fr.alng.footapi.service.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class RoomRoutine {

    private final ModelConverter<Room, RoomDTO> modelConverterRoom = new ModelConverter<>();

    @JsonIgnoreProperties(ignoreUnknown = true)
    public void run(RoomRepository roomRepository, MatchRepository matchRepository, RoomService roomService) {

        LocalDate dateFrom = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusDays(1);
        LocalDate dateTo = dateFrom.plusDays(8);

        List<Match> matchList = matchRepository.findMatchesBetweenDates(
                Date.from(dateFrom.atStartOfDay(ZoneId.systemDefault()).toInstant()),
                Date.from(dateTo.atStartOfDay(ZoneId.systemDefault()).toInstant())
        );

        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setBalance(0);
        roomDTO.setCashPrice(false);
        roomDTO.setDateFrom(Date.from(dateFrom.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        roomDTO.setDateTo(Date.from(dateTo.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        roomDTO.setName("Game" + dateFrom.getDayOfYear());
        roomDTO.setPlayerLimit(50);
        roomDTO.setStatus("CREATED");
        Room room = modelConverterRoom.convertDtoToEntity(roomDTO, Room.class);
        roomRepository.save(room);

        for (LocalDate date = dateFrom; date.isBefore(dateTo); date = date.plusDays(1)) {
            List<Match> tempMatchApiList = new ArrayList<>();
            LocalDate finalDate = date;
            matchList.forEach((Match match) -> {
                LocalDate utcDate = match.getUtcDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                if (utcDate.equals(finalDate)) tempMatchApiList.add(match);
            });
            int listSize = tempMatchApiList.size();
            if(listSize > 0) {
                SecureRandom random = new SecureRandom();
                int index = random.nextInt(listSize);
                Match matchChosed = tempMatchApiList.get(index);
                roomService.addMatchToRoom(matchChosed.getApiId(), room.getId());
                tempMatchApiList.remove(index);
                listSize = tempMatchApiList.size();
                matchChosed = tempMatchApiList.get((random.nextInt(listSize)));
                roomService.addMatchToRoom(matchChosed.getApiId(), room.getId());
            }else {
                log.info("no match found at "+finalDate);
            }

        }
        if(roomService.getRoom(room.getId()).get().getMatches().size() < 4){
            log.info("Room deleted, at least 4 matches needed");
            roomRepository.delete(room);
        }
    }

    public void setStatusRoom(RoomService roomService, RoomRepository roomRepository){
        List<Room> roomList = roomService.getRooms();
        roomList.forEach((Room room) -> {

            if(room.getStatus().equalsIgnoreCase("CREATED") && isInPlayMatch(room).getStatus().equalsIgnoreCase("IN_PLAY")){
                room.setStatus("PLAYING");
            } else if ((room.getStatus().equalsIgnoreCase("PLAYING") || room.getStatus().equalsIgnoreCase("CREATED")) && allMatchesFinished(room) == null) {
                room.setStatus("FINISHED");
            }
            roomRepository.save(room);

        });
    }

    public Match isInPlayMatch(Room room){
        Match m = new Match();
        m.setStatus("");
        return room.getMatches().stream().filter(matche -> matche.getStatus().equalsIgnoreCase("IN_PLAY"))
                .findFirst().orElse(m);

    }

    public Match allMatchesFinished(Room room){
        return room.getMatches().stream().filter(matche -> !matche.getStatus().equalsIgnoreCase("FINISHED"))
                .findFirst().orElse(null);
    }
}