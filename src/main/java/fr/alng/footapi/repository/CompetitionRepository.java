/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.repository;
import fr.alng.footapi.model.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {
    Competition findByApiId(Long id);
}
