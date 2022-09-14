/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.dto;

import lombok.Data;

import java.util.Date;

@Data
public class SeasonDTO {
    private Long id;
    private Long apiId;
    private Date startDate;
    private Date endDate;
    private int matchday;
    private CompetitionDTO competitions;
}
