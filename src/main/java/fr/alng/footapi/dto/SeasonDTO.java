/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.dto;

import fr.alng.footapi.model.Competition;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.Set;

@Data
public class SeasonDTO {
    private Long apiId;
    private Date startDate;
    private Date endDate;
    private int matchday;
    private CompetitionDTO competitions;
}
