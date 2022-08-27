/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "buffer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Buffer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long apiId;
    private String url;
    private boolean isExecuted;
    private String type;
}
