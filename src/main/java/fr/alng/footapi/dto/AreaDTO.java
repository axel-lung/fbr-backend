/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.dto;

import lombok.Data;

@Data
public class AreaDTO {
    private String name;
    private String countryCode;
    private String flag;
    private Long apiId;
    private AreaDTO parentArea;
}
