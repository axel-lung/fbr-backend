/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.service;

import fr.alng.footapi.model.Bet;
import fr.alng.footapi.repository.BetRepository;

import java.util.List;
import java.util.Optional;

public class BetServiceImpl implements BetService{

    private BetRepository betRepository;

    @Override
    public Bet saveBet(Bet bet) {
        return betRepository.save(bet);
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
