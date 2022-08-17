/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.scheduler;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.alng.footapi.externalapi.RoomRoutine;
import fr.alng.footapi.service.RoomService;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ScheduleConfig {

    private final RoomRoutine roomRoutine = new RoomRoutine();
    private RoomService roomService;

    @Scheduled(fixedDelay = 10000)
    public void runRoomRoutine() throws JsonProcessingException {
        roomRoutine.run(roomService);
    }
}
