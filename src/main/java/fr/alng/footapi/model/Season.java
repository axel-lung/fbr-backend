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
@Table(name = "season")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Season {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long apiId;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    private int matchday;

    @OneToMany(mappedBy = "season")
    Set<Competition> competitions;
}