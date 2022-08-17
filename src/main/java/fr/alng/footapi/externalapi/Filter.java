/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.externalapi;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class Filter {
    private Long id;
    private List<Long> ids;
    private int matchday;
    private String season;
    private final List<Status> status;
    private Venue venue;
    private String date;
    private Date dateFrom;
    private Date dateTo;
    private Stage stage;
    private String plan;
    private String competitions;
    private String areas;
    private String group;
    private int limit;
    private int offset;
    private String permission;

    public Filter(){
        status = new ArrayList<>();
    }
}
