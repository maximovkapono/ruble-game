package com.kapono.ruble.game.repository;

import com.kapono.ruble.game.entity.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GameRepository extends JpaRepository<GameEntity, Long> {

    @Query("select g from GameEntity g where g.winner is null and g.date > current_timestamp")
    GameEntity findCurrentGame();

}
