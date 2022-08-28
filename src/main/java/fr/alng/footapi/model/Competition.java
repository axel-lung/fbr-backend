/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "competition")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Competition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long apiId;

    private String name;

    private String code;

    private String type;

    private String emblem;

    private String plan;

    @ManyToOne
    @JoinColumn(name = "area")
    private Area area;

    @ManyToOne
    @JoinColumn(name = "season")
    private Season season;

    @OneToMany(mappedBy = "competition")
        @JsonIgnore
    Set<Match> matches;
}