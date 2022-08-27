/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.externalapi;

import lombok.Getter;

@Getter
public class CompetitionApi {
    private Long id;
    private String name;
    private String code;
    private String type;
    private String emblem;
    private AreaApi area;
}
