/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.dto;

import fr.alng.footapi.model.Bet;
import fr.alng.footapi.model.Role;
import fr.alng.footapi.model.Room;
import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Data
public class UserDTO {
    private String username;
    private String email;
    private Collection<Role> roles = new ArrayList<>();
    private Date birthday;
    private String password;
    private BetDTO bets;
    private List<RoomDTO> userRooms;
}
