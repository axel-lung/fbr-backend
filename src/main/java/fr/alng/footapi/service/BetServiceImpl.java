/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.service;

import fr.alng.footapi.converter.ModelConverter;
import fr.alng.footapi.dto.BetDTO;
import fr.alng.footapi.model.Bet;
import fr.alng.footapi.repository.BetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class BetServiceImpl implements BetService{
    private final BetRepository betRepository;
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
