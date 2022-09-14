/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.dto;

import fr.alng.footapi.model.Match;
import fr.alng.footapi.model.Room;
import fr.alng.footapi.model.Team;
import fr.alng.footapi.model.User;
import lombok.Data;

@Data
public class BetDTO {
    private Long id;
    private Boolean hasWin;
    private Team team;
    private Match matche;
    private User user;
    private Room room;
}
