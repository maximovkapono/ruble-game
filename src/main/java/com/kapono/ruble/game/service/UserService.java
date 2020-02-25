package com.kapono.ruble.game.service;

import com.kapono.ruble.game.entity.GameEntity;
import com.kapono.ruble.game.entity.UsersEntity;
import com.kapono.ruble.game.repository.GameRepository;
import com.kapono.ruble.game.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UsersRepository usersRepository;
    private final GameRepository gameRepository;

    @Transactional
    public void addNewUser(Integer userId) {
        GameEntity currentGame = gameRepository.findCurrentGame();
        if (currentGame != null && !usersRepository.existsByUserIdAndGame(userId, currentGame)) {
            UsersEntity usersEntity = new UsersEntity();
            usersEntity.setGame(currentGame);
            usersEntity.setUserId(userId);
            usersRepository.save(usersEntity);
        }
    }

}
