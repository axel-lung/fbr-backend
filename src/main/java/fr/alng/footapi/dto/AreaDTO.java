/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.dto;

import lombok.Data;

@Data
public class AreaDTO {
    private Long id;
    private Long apiId;
    private String name;
    private String countryCode;
    private String flag;
    private AreaDTO parentArea;
}
