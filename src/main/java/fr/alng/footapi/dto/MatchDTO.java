/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.dto;

import fr.alng.footapi.model.Bet;
import fr.alng.footapi.model.Room;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;

@Data
public class MatchDTO {
    private Long apiId;
    private Date utcDate;
    private String status;
    private int matchday;
    private String stage;
    private String duration;
    private int homeScore;
    private int awayScore;
    private String groupe;
    private BetDTO bet;
    private List<RoomDTO> rooms;
}
