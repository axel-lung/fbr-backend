/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.service;

import fr.alng.footapi.converter.ModelConverter;
import fr.alng.footapi.dto.BetDTO;
import fr.alng.footapi.model.Bet;
import fr.alng.footapi.repository.BetRepository;

import java.util.List;
import java.util.Optional;

public class BetServiceImpl implements BetService{
    private BetRepository betRepository;
    private final ModelConverter<Bet, BetDTO> modelConverter = new ModelConverter<>();

    @Override
    public BetDTO saveBet(BetDTO betDTO) {
        Bet bet = modelConverter.convertDtoToEntity(betDTO, Bet.class);
        bet = betRepository.save(bet);
        return modelConverter.convertEntityToDto(bet, BetDTO.class);
    }

    @Override
    public Optional<Bet> getBet(Long id) {
        return betRepository.findById(id);
    }

    @Override
    public List<Bet> getBets() {
        return betRepository.findAll();
    }
}
