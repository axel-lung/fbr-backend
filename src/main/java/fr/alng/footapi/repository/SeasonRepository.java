/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.repository;

import fr.alng.footapi.model.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeasonRepository extends JpaRepository<Season, Long> {
    Season findByApiId(Long id);
}
