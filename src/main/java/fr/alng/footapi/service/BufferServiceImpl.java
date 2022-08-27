/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.service;

import fr.alng.footapi.converter.ModelConverter;
import fr.alng.footapi.dto.BufferDTO;
import fr.alng.footapi.model.Buffer;
import fr.alng.footapi.repository.BufferRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class BufferServiceImpl implements BufferService{

    private final BufferRepository bufferRepository;
    private final ModelConverter<Buffer, BufferDTO> modelConverter = new ModelConverter<>();

    @Override
    public BufferDTO saveBuffer(BufferDTO bufferDTO) {
        Buffer buffer = modelConverter.convertDtoToEntity(bufferDTO, Buffer.class);
        buffer = bufferRepository.save(buffer);
        return modelConverter.convertEntityToDto(buffer, BufferDTO.class);
    }


    @Override
    public List<Buffer> findByType(String type, boolean isExecuted) {
        return bufferRepository.findByType(type, isExecuted);
    }
}
