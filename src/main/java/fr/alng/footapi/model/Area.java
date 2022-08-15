/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "area")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(name = "country_code")
    private String countryCode;

    private String flag;

    @OneToMany(mappedBy = "area")
    Set<Competition> competitions;

    @OneToMany(mappedBy = "area")
    Set<Match> matches;

    @OneToMany(mappedBy = "parentArea")
    Set<Area> areas;

    @ManyToOne
    @JoinColumn(name = "parent_area_id")
    private Area parentArea;
}