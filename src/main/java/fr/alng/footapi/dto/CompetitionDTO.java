/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.dto;

import lombok.Data;

@Data
public class CompetitionDTO {
    private Long id;
    private Long apiId;
    private String name;
    private String code;
    private String type;
    private String emblem;
    private AreaDTO area;
    private SeasonDTO seasons;
}
