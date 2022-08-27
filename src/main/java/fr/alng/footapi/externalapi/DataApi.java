/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.externalapi;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class DataApi {
    private Filter filters;
    private ResultSet resultSet;
    private final List<MatchApi> matches;

    public DataApi() {
        matches = new ArrayList<>();
    }
}
