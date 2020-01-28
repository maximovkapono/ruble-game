package com.kapono.ruble.game.repository;

import com.kapono.ruble.game.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<UsersEntity, Long> {
}
