/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.externalapi;

import fr.alng.footapi.dto.AreaDTO;
import fr.alng.footapi.dto.CompetitionDTO;
import fr.alng.footapi.dto.MatchDTO;
import fr.alng.footapi.dto.TeamDTO;
import fr.alng.footapi.model.Buffer;
import fr.alng.footapi.repository.BufferRepository;
import fr.alng.footapi.service.AreaService;
import fr.alng.footapi.service.CompetitionService;
import fr.alng.footapi.service.MatchService;
import fr.alng.footapi.service.TeamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@Slf4j
public class DataRoutine {

    private BufferRepository bufferRepository;
    public void run(BufferRepository bufferRepository, AreaService areaService,
                    CompetitionService competitionService, TeamService teamService,
                    MatchService matchService){
        this.bufferRepository = bufferRepository;
        Buffer buffer = findInBuffer();
        if(buffer != null){
            switch (buffer.getType()) {
                case "Area" -> {
                    AreaApi areaApi = areaRequest(buffer.getUrl()).getBody();
                    AreaDTO areaDTO = new AreaDTO();
                    assert areaApi != null;
                    areaDTO.setApiId(areaApi.getId());
                    areaDTO.setName(areaApi.getName());
                    areaDTO.setCountryCode(areaApi.getCode());
                    areaDTO.setFlag(areaApi.getFlag());
                    if (areaService.getAreaByApiId(areaApi.getId()) == null) {
                        areaService.saveArea(areaDTO);
                    } else {
                        areaService.updateArea(areaApi.getId(), areaApi);
                    }
                }
                case "Competition" -> {
                    CompetitionApi competitionApi = competitionRequest(buffer.getUrl()).getBody();
                    CompetitionDTO competitionDTO = new CompetitionDTO();
                    assert competitionApi != null;
                    competitionDTO.setApiId(competitionApi.getId());
                    competitionDTO.setName(competitionApi.getName());
                    competitionDTO.setEmblem(competitionApi.getEmblem());
                    competitionDTO.setCode(competitionApi.getCode());
                    if (competitionService.getCompetitionByApiId(competitionApi.getId()) == null) {
                        competitionService.saveCompetition(competitionDTO);
                        competitionService.addAreaToCompetition(competitionApi.getArea().getId(), competitionApi.getId());
                    }else {
                        competitionService.updateCompetition(competitionApi.getId(), competitionApi);
                    }
                }
                case "Team" -> {
                    TeamApi teamApi = teamRequest(buffer.getUrl()).getBody();
                    TeamDTO teamDTO = new TeamDTO();
                    assert teamApi != null;
                    teamDTO.setApiId(teamApi.getId());
                    teamDTO.setName(teamApi.getName());
                    teamDTO.setShortName(teamApi.getShortName());
                    teamDTO.setCrest(teamApi.getCrest());
                    teamDTO.setTla(teamApi.getTla());
                    if (teamService.getTeamByApiId(teamApi.getId()) == null){
                        teamService.saveTeam(teamDTO);
                    }else {
                        teamService.updateTeam(teamApi.getId(), teamApi);
                    }
                }
                case "Match" -> {
                    MatchApi matchApi = matchRequest(buffer.getUrl()).getBody();
                    MatchDTO matchDTO = new MatchDTO();
                    assert matchApi != null;
                    matchDTO.setApiId(matchApi.getId());
                    matchDTO.setStage(matchApi.getStage().toString());
                    matchDTO.setUtcDate(matchApi.getUtcDate());
                    matchDTO.setGroupe(matchApi.getGroup());
                    matchDTO.setStatus(matchApi.getStatus().toString());
                    matchDTO.setMatchday(matchApi.getMatchday());
                    matchDTO.setDuration(matchApi.getScore().getDuration());
                    matchDTO.setHomeScore(matchApi.getScore().getFullTime().getHome());
                    matchDTO.setAwayScore(matchApi.getScore().getFullTime().getAway());
                    if (matchService.getMatchByApiId(matchApi.getId()) == null){
                        matchService.saveMatch(matchDTO);
                        matchService.addAreaToMatch(matchApi.getArea().getId(), matchApi.getId());
                        matchService.addCompetitionToMatch(matchApi.getCompetition().getId(), matchApi.getId());
                        matchService.addHomeTeamToMatch(matchApi.getHomeTeam().getId(), matchApi.getId());
                        matchService.addAwayTeamToMatch(matchApi.getAwayTeam().getId(), matchApi.getId());
                    }else {
                        matchService.updateMatch(matchApi.getId(), matchApi);
                    }
                }
                default -> {
                    log.error("Unknown element in buffer");
                }
            }
        }else {
            return;
        }
        bufferRepository.delete(buffer);
    }

    public Buffer findInBuffer(){

        List<Buffer> areaApis = bufferRepository.findByType("Area", false);

        if(!areaApis.isEmpty()){
            return areaApis.get(0);
        }else {
            List<Buffer> competitionApis = bufferRepository.findByType("Competition", false);
            if(!competitionApis.isEmpty()){
                return competitionApis.get(0);
            }else{
                List<Buffer> teamApis = bufferRepository.findByType("Team", false);
                if(!teamApis.isEmpty()){
                    return teamApis.get(0);
                }else{
                    List<Buffer> matchApis = bufferRepository.findByType("Match", false);
                    if(!matchApis.isEmpty()){
                        return matchApis.get(0);
                    }
                }
            }
        }
        return null;
    }

    public ResponseEntity<AreaApi> areaRequest(String url){
        final RestTemplate restTemplate = new RestTemplate();
        final HttpHeaders headers = new HttpHeaders();
        headers.set("X-Auth-Token", "");
        HttpEntity<String> request = new HttpEntity<>(headers);
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                AreaApi.class
        );
    }

    public ResponseEntity<CompetitionApi> competitionRequest(String url){
        final RestTemplate restTemplate = new RestTemplate();
        final HttpHeaders headers = new HttpHeaders();
        headers.set("X-Auth-Token", "");
        HttpEntity<String> request = new HttpEntity<>(headers);
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                CompetitionApi.class
        );
    }

    public ResponseEntity<TeamApi> teamRequest(String url){
        final RestTemplate restTemplate = new RestTemplate();
        final HttpHeaders headers = new HttpHeaders();
        headers.set("X-Auth-Token", "");
        HttpEntity<String> request = new HttpEntity<>(headers);
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                TeamApi.class
        );
    }

    public ResponseEntity<MatchApi> matchRequest(String url){
        final RestTemplate restTemplate = new RestTemplate();
        final HttpHeaders headers = new HttpHeaders();
        headers.set("X-Auth-Token", "");
        HttpEntity<String> request = new HttpEntity<>(headers);
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                MatchApi.class
        );
    }

}
