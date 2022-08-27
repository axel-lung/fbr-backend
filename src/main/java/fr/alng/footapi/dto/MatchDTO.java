/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MatchDTO {
    private Long apiId;
    private Date utcDate;
    private String status;
    private int matchday;
    private String stage;
    private String duration;
    private int homeScore;
    private int awayScore;
    private String groupe;
    private AreaDTO area;
    private CompetitionDTO competition;
    private SeasonDTO season;
    private TeamDTO homeTeam;
    private TeamDTO awayTeam;
    private TeamDTO winnerTeam;
}
