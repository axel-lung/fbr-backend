/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.dto;

import fr.alng.footapi.model.Match;
import lombok.Data;

import javax.persistence.OneToMany;
import java.util.Set;

@Data
public class TeamDTO {
    private Long apiId;
    private String name;
    private String shortName;
    private String tla;
    private String crest;
    private MatchDTO homeTeamMatches;
    private MatchDTO awayTeamMatches;
    private MatchDTO winnerTeamMatches;
}
