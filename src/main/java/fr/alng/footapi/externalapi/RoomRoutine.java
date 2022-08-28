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
        roomDTO.setName("Room" + dateFrom.getDayOfYear());
        roomDTO.setPlayerLimit(50);
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
                Match matchChosed = tempMatchApiList.get((random.nextInt(listSize)));
                roomService.addMatchToRoom(matchChosed.getApiId(), room.getId());
            }else {
                log.info("no matches on "+finalDate);
            }

        }
    }
}