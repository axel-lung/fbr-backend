/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "bet")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Boolean hasWin;

    @ManyToOne
    @JoinColumn(name = "team")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "matche")
    private Match matche;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "room")
    private Room room;
}
