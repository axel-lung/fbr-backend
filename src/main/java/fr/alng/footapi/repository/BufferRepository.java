/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.repository;

import fr.alng.footapi.model.Buffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BufferRepository extends JpaRepository<Buffer, Long> {
    @Query("select b from Buffer b where b.type = ?1 and b.isExecuted = ?2")
    List<Buffer> findByType(String type, boolean isExecuted);

    Buffer findByApiId(Long id);
}
