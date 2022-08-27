/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.service;

import fr.alng.footapi.dto.BufferDTO;
import fr.alng.footapi.model.Buffer;

import java.util.List;

public interface BufferService {
    BufferDTO saveBuffer(BufferDTO bufferDTO);

    List<Buffer> findByType(String type, boolean isExecuted);
}
