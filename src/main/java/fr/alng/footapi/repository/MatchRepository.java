/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.repository;

import fr.alng.footapi.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    Match findByApiId(Long id);

    @Query("select m from Match m where m.utcDate >= ?1 and m.utcDate <= ?2")
    List<Match> findMatchesBetweenDates(Date dateFrom, Date dateTo);
}