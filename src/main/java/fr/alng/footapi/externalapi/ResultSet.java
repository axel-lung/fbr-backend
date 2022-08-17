/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.externalapi;

import lombok.Getter;

@Getter
public class ResultSet {
    private int count;
    private String competitions;
    private String first;
    private String last;
    private int played;
}
