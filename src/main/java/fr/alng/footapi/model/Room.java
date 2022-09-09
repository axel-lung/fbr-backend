/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

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

    @ManyToMany
    @JoinTable( name = "room_match",
            joinColumns = @JoinColumn( name = "room_id" ),
            inverseJoinColumns = @JoinColumn( name = "match_id" ) )
    private Collection<Match> matches = new ArrayList<>();

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "user_room",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    Collection<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "betRoom")
    @JsonIgnore
    Set<Bet> bets;
}
