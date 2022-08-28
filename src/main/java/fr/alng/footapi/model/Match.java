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
@Table(name = "matche")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long apiId;
    @Column(name = "date_utc")
    private Date utcDate;
    private String status;
    private Integer matchday;
    private String stage;
    private String duration;
    @Column(name = "home_score")
    private Integer homeScore;
    @Column(name = "away_score")
    private Integer awayScore;
    private String groupe;

    @ManyToOne
    @JoinColumn(name = "area")
    private Area area;

    @ManyToOne
    @JoinColumn(name = "competition")
    private Competition competition;

    @ManyToOne
    @JoinColumn(name = "homeTeam")
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "awayTeam")
    private Team awayTeam;

    @ManyToOne
    @JoinColumn(name = "winnerTeam")
    private Team winnerTeam;

    @OneToMany(mappedBy = "betMatche")
    @JsonIgnore
    Set<Bet> bets;

    @ManyToMany
    @JoinTable(
        name = "room_match",
        joinColumns = @JoinColumn(name = "match_id"),
        inverseJoinColumns = @JoinColumn(name = "room_id"))
    Collection<Room> rooms = new ArrayList<>();

}
