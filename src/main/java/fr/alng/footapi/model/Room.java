/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "room")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Column(name = "player_limit")
    private int playerLimit;
    @Column(name = "date_from")
    private Date dateFrom;
    @Column(name = "date_to")
    private Date dateTo;
    private float balance;
    @Column(name = "is_cash_price")
    private boolean isCashPrice;

    @ManyToMany(mappedBy = "rooms")
    Set<Match> matches;

    @ManyToMany(mappedBy = "userRooms")
    Set<User> users;
}
