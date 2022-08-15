/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.repository;
import fr.alng.footapi.model.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {
}
