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
    private RoomService roomService;
    RoomRoutine roomRoutine = new RoomRoutine();
    DataRoutine dataRoutine = new DataRoutine();

    FillBufferRoutine fillBufferRoutine = new FillBufferRoutine();

    @Scheduled(fixedDelay = 6000000)
    public void runRoomRoutine() throws JsonProcessingException {
        roomRoutine.run(roomRepository, matchRepository, roomService);
    }
    @Scheduled(fixedRate = 7000)
    public void runDataRoutine(){
        log.info("DataRoutine running...");
        dataRoutine.run(bufferRepository, areaService, competitionService, teamService, matchService);
    }
    @Scheduled(fixedRate = 86400000)
    public void runFillBufferRoutine(){
        log.info("Fill Buffer running...");
        fillBufferRoutine.run(bufferRepository);
    }
}
