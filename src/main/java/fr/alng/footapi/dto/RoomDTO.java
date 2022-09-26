/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class RoomDTO {
    private Long id;
    private String name;
    private int playerLimit;
    private Date dateFrom;
    private Date dateTo;
    private float balance;
    private boolean isCashPrice;
    private String status;
    private List<MatchDTO> matches;
    private List<UserDTO> users;
}
