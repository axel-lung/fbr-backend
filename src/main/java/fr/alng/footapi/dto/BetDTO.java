/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.dto;

import fr.alng.footapi.model.Match;
import fr.alng.footapi.model.Team;
import fr.alng.footapi.model.User;
import lombok.Data;

@Data
public class BetDTO {
    private Team team;
    private Match match;
    private User user;
}
