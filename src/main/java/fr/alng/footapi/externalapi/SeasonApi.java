/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.externalapi;

import lombok.Getter;

import java.util.Date;

@Getter
public class SeasonApi {
    private Long id;
    private Date startDate;
    private Date endDate;
    private int currentMatchday;
}
