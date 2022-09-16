/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.externalapi;

import fr.alng.footapi.model.Buffer;
import fr.alng.footapi.repository.BufferRepository;
import lombok.Getter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Component
public class FillBufferRoutine {

    String TOKEN = "";

    public void run(BufferRepository bufferRepository){
        ResponseEntity<MatchApiDTO> matchApiDTOResponseEntity = getMatchApis();
        Objects.requireNonNull(matchApiDTOResponseEntity.getBody()).getMatches().forEach((MatchBufferDTO match) -> {
            if(bufferRepository.findByApiId(match.getArea().getId()) == null){
                bufferRepository.save(new Buffer(
                        null,
                        match.getArea().getId(),
                        "https://api.football-data.org/v4/areas/"+match.getArea().getId().toString(),
                        false,
                        "Area")
                );
            }
            if(bufferRepository.findByApiId(match.getCompetition().getId()) == null){
                bufferRepository.save(new Buffer(
                        null,
                        match.getCompetition().getId(),
                        "https://api.football-data.org/v4/competitions/"+match.getCompetition().getId().toString(),
                        false,
                        "Competition")
                );
            }
            if(bufferRepository.findByApiId(match.getHomeTeam().getId()) == null){
                bufferRepository.save(new Buffer(
                        null,
                        match.getHomeTeam().getId(),
                        "https://api.football-data.org/v4/teams/"+match.getHomeTeam().getId().toString(),
                        false,
                        "Team")
                );
            }
            if(bufferRepository.findByApiId(match.getAwayTeam().getId()) == null){
                bufferRepository.save(new Buffer(
                        null,
                        match.getAwayTeam().getId(),
                        "https://api.football-data.org/v4/teams/"+match.getAwayTeam().getId().toString(),
                        false,
                        "Team")
                );
            }
            if(bufferRepository.findByApiId(match.getId()) == null){
                bufferRepository.save(new Buffer(
                        null,
                        match.getId(),
                        "https://api.football-data.org/v4/matches/"+match.getId().toString(),
                        false,
                        "Match")
                );
            }
        });
    }

    public ResponseEntity<MatchApiDTO> getMatchApis(){
        final RestTemplate restTemplate = new RestTemplate();
        final HttpHeaders headers = new HttpHeaders();
        headers.set("X-Auth-Token", TOKEN);
        HttpEntity<String> request = new HttpEntity<>(headers);
        LocalDate dateFrom = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().minusDays(1);
        LocalDate dateTo = dateFrom.plusDays(8);
        return restTemplate.exchange(
                "https://api.football-data.org/v4/matches?dateFrom="+dateFrom+"&dateTo="+dateTo+"",
                HttpMethod.GET,
                request,
                MatchApiDTO.class
        );
    }
}

@Getter
class MatchApiDTO{
    private Filter filters;
    private ResultSet resultSet;
    private final List<MatchBufferDTO> matches;

    public MatchApiDTO() {
        matches = new ArrayList<>();
    }
}

@Getter
class MatchBufferDTO{
    private AreaBufferDTO area;
    private CompetitionBufferDTO competition;
    private Long id;
    private TeamBufferDTO awayTeam;
    private TeamBufferDTO homeTeam;
}
@Getter
class AreaBufferDTO{
    private Long id;
}
@Getter
class CompetitionBufferDTO{
    private Long id;
}
@Getter
class TeamBufferDTO{
    private Long id;
}
