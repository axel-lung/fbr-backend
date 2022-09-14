/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "team")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long apiId;
    private String name;
    @Column(name = "short_name")
    private String shortName;
    private String tla;
    private String crest;

    public Team(Long id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "homeTeam")
    @JsonIgnore
    Set<Match> homeTeamMatches;

    @OneToMany(mappedBy = "awayTeam")
    @JsonIgnore
    Set<Match> awayTeamMatches;

    @OneToMany(mappedBy = "winnerTeam")
    @JsonIgnore
    Set<Match> winnerTeamMatches;

    @OneToMany(mappedBy = "team")
    @JsonIgnore
    Set<Bet> bets;

}
