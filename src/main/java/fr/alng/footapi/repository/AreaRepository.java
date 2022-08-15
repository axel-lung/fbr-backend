/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.repository;
import fr.alng.footapi.model.Area;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AreaRepository extends JpaRepository<Area, Long> {
}
