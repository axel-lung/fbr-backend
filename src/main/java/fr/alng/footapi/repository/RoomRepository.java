/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.repository;

import fr.alng.footapi.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}
