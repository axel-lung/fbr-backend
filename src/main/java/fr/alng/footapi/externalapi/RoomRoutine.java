/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.externalapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.mysql.cj.xdevapi.JsonArray;
import fr.alng.footapi.model.Room;
import fr.alng.footapi.service.RoomService;
import lombok.Getter;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


public class RoomRoutine {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public void run(RoomService roomService) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Auth-Token", "129f8612ad83483b83282f62998e4509");
        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<MatchApiImpl> matchApiImpl = restTemplate.exchange(
                "https://api.football-data.org/v4/matches?status=SCHEDULED&date=2022-08-16",
                HttpMethod.GET,
                request,
                MatchApiImpl.class
        );

        Objects.requireNonNull(matchApiImpl.getBody()).getMatches().forEach((MatchApi matchApi) -> {
            System.out.println(matchApi.getId());
        });



        //System.out.println(Objects.requireNonNull(matchApiImpl.getBody()).getFilters().getStatus());


        //roomService.saveRoom(new Room());
    }


}
@Getter
class MatchApiImpl{
    private Filter filters;
    private ResultSet resultSet;
    private final List<MatchApi> matches;

    public MatchApiImpl() {
        matches = new ArrayList<>();
    }
}