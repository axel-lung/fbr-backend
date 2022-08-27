/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.repository;
import fr.alng.footapi.model.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaRepository extends JpaRepository<Area, Long> {
    Area findByApiId(Long apiId);
}
