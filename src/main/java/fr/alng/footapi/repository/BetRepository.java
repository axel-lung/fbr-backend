/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.repository;

import fr.alng.footapi.model.Bet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BetRepository extends JpaRepository<Bet, Long> {
    @Query("select b from Bet b where b.betUser.id = ?1 and b.betMatche.id = ?2 and b.betRoom.id = ?3")
    Bet findBetByUserMatchRoom(Long userId, Long matchId, Long roomId);
}
