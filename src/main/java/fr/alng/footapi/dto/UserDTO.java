/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.dto;

import fr.alng.footapi.model.Role;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private Collection<Role> roles = new ArrayList<>();
    private Date birthday;
    private String password;
    private BetDTO bets;
    private List<RoomDTO> userRooms;
}