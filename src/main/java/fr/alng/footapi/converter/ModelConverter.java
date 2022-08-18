/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ModelConverter<E, D> {

    public D convertEntityToDto(E source, Class<D> destinationType) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(source, destinationType);
    }

    public E convertDtoToEntity(D source, Class<E> destinationType) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(source, destinationType);
    }
}
