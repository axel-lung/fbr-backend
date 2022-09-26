/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.scheduler;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.alng.footapi.externalapi.DataRoutine;
import fr.alng.footapi.externalapi.FillBufferRoutine;
import fr.alng.footapi.externalapi.RoomRoutine;
import fr.alng.footapi.repository.BufferRepository;
import fr.alng.footapi.repository.MatchRepository;
import fr.alng.footapi.repository.RoomRepository;
import fr.alng.footapi.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@Slf4j
public class ScheduleConfig {
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private BufferRepository bufferRepository;
    @Autowired
    private AreaService areaService;
    @Autowired
    private CompetitionService competitionService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private MatchService matchService;
    @Autowired
    private BetService betService;
    @Autowired
    private RoomService roomService;
    RoomRoutine roomRoutine = new RoomRoutine();
    DataRoutine dataRoutine = new DataRoutine();
    VictoryRoutine victoryRoutine = new VictoryRoutine();
    FillBufferRoutine fillBufferRoutine = new FillBufferRoutine();

    @Scheduled(cron = "0 0 6 * * *", zone = "Europe/Paris")
    public void runRoomRoutine() throws JsonProcessingException {
        log.info("RoomRoutine running...");
        roomRoutine.run(roomRepository, matchRepository, roomService);
    }
    @Scheduled(cron = "*/7 * * * * *", zone = "Europe/Paris")
    public void runDataRoutine(){
        log.info("DataRoutine running...");
        dataRoutine.run(bufferRepository, areaService, competitionService, teamService, matchService);
    }
    @Scheduled(cron = "0 0 */1 * * *", zone = "Europe/Paris")
    public void runFillBufferRoutine(){
        log.info("Fill Buffer running...");
        fillBufferRoutine.run(bufferRepository);
    }
    @Scheduled(cron = "0 */5 * * * *", zone = "Europe/Paris")
    public void runEveryFiveMinutes(){
        log.info("VictoryRoutine running...");
        log.info("setStatusRoom running...");
        victoryRoutine.run(betService, matchService);
        roomRoutine.setStatusRoom(roomService, roomRepository);
    }
}
