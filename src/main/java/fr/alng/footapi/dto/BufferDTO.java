/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.dto;

import lombok.Data;

@Data
public class BufferDTO {
    private Long apiId;
    private String url;
    private boolean isExecuted;
    private String type;
}
