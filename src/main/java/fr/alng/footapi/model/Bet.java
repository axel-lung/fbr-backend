/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.model;

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

    @ManyToOne
    @JoinColumn(name = "betTeam")
    private Team betTeam;

    @ManyToOne
    @JoinColumn(name = "betMatche")
    private Match betMatche;

    @ManyToOne
    @JoinColumn(name = "betUser")
    private User betUser;
}
