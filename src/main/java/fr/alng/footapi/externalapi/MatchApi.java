/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.externalapi;

import lombok.Getter;

import java.util.Date;

@Getter
public class MatchApi {
    private AreaApi area;
    private CompetitionApi competition;
    private SeasonApi season;
    private Long id;
    private Date utcDate;
    private Status status;
    private int matchday;
    private Stage stage;
    private String group;
    private Date lastUpdated;
    private TeamApi homeTeam;
    private TeamApi awayTeam;
    private ScoreApi score;
}
