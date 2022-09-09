/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.service;

import fr.alng.footapi.dto.BetDTO;
import fr.alng.footapi.model.Bet;

import java.util.List;
import java.util.Optional;

public interface BetService {
    BetDTO saveBet(BetDTO betDTO);
    Optional<Bet> getBet(Long id);
    List<Bet> getBets();
    Bet findBetByUserMatchRoom(Long userId, Long matchId, Long roomId);
}
