/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.service;

import fr.alng.footapi.model.Bet;

import java.util.List;
import java.util.Optional;

public interface BetService {
    Bet saveBet(Bet bet);
    Optional<Bet> getBet(Long id);
    List<Bet> getBets();
}
