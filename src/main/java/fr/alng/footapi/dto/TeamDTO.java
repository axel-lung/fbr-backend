/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.dto;

import lombok.Data;

@Data
public class TeamDTO {
    private Long id;
    private Long apiId;
    private String name;
    private String shortName;
    private String tla;
    private String crest;
    private MatchDTO homeTeamMatches;
    private MatchDTO awayTeamMatches;
    private MatchDTO winnerTeamMatches;
}
