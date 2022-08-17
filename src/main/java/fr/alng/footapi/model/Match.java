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
    private int matchday;
    private String stage;
    private String duration;
    @Column(name = "home_score")
    private int homeScore;
    @Column(name = "away_score")
    private int awayScore;
    private String groupe;

    @ManyToOne
    @JoinColumn(name = "area_id")
    private Area area;

    @ManyToOne
    @JoinColumn(name = "competition_id")
    private Competition competition;

    @ManyToOne
    @JoinColumn(name = "home_team_id")
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "away_team_id")
    private Team awayTeam;

    @ManyToOne
    @JoinColumn(name = "winner_team_id")
    private Team winnerTeam;

    @OneToMany(mappedBy = "match")
    Set<Bet> bets;

    @ManyToMany
    @JoinTable(
        name = "room_match",
        joinColumns = @JoinColumn(name = "match_id"),
        inverseJoinColumns = @JoinColumn(name = "room_id"))
    Set<Room> rooms;

}
