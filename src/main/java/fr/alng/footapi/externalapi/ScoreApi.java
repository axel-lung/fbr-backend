/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.externalapi;

import lombok.Getter;

@Getter
public class ScoreApi {
    private String winner;
    private String duration;
    private FullTime fullTime;
}
@Getter
class FullTime{
    private int home;
    private int away;
}
