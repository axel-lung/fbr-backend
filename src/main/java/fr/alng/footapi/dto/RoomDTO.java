/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.dto;

import fr.alng.footapi.model.Match;
import fr.alng.footapi.model.User;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;

@Data
public class RoomDTO {
    private String name;
    private int playerLimit;
    private Date dateFrom;
    private Date dateTo;
    private float balance;
    private boolean isCashPrice;
    private List<MatchDTO> matches;
    private List<UserDTO> users;
}
