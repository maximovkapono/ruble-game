package com.kapono.ruble.game.bot.command.handler;

import com.kapono.ruble.game.entity.GameEntity;
import com.kapono.ruble.game.repository.GameRepository;
import com.kapono.ruble.game.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class PrizeService {

    private final GameRepository gameRepository;
    private final UsersRepository usersRepository;

    public SendMessage response(Message message) {
        String responseMessage = createResponseMessage();
        return new SendMessage(message.getChatId(), responseMessage);
    }

    private String createResponseMessage() {
        GameEntity currentGame = gameRepository.findCurrentGame();
        if (currentGame != null) {
            int usersCount = usersRepository.countAllByGame(currentGame);
            int currentPrize = usersCount * currentGame.getCost();
            LocalDateTime finishDate = currentGame.getDate();
            return "Дата розыгрыша: " + finishDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) +
                    "; текущий выигрыш: " + currentPrize + "р.";
        }
        return "На данный момент розыгрыш не запущен";
    }
}
