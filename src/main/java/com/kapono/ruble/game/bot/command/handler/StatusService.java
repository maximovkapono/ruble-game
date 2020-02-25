package com.kapono.ruble.game.bot.command.handler;

import com.kapono.ruble.game.entity.GameEntity;
import com.kapono.ruble.game.repository.GameRepository;
import com.kapono.ruble.game.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
@RequiredArgsConstructor
public class StatusService {

    private final GameRepository gameRepository;
    private final UsersRepository usersRepository;

    public SendMessage response(Message message) {
        String responseMessage = getResponseMessage(message);
        return new SendMessage(message.getChatId(), responseMessage);
    }

    private String getResponseMessage(Message message) {
        GameEntity currentGame = gameRepository.findCurrentGame();
        Integer userId = message.getFrom().getId();
        if (currentGame != null && usersRepository.existsByUserIdAndGame(userId, currentGame)) {
            return "Вы учавствуете в текущем розыгрыше";
        }
        return "Вы не учавствуете в текущем розыгрыше";
    }
}
