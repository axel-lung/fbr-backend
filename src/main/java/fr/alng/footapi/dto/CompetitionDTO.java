/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.dto;

import fr.alng.footapi.model.Match;
import lombok.Data;

@Data
public class CompetitionDTO {
    private Long apiId;

    private String name;

    private String code;

    private String type;

    private String emblem;

    private String plan;

    private MatchDTO match;
}
