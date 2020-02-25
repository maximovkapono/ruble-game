package com.kapono.ruble.game.bot.command.handler;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
public class StartService {

    public static final String TEXT = "В этом боте ты можешь выиграть деньги! Нажми /prize, " +
            "чтобы узнать дату розыгрыша и /pay чтобы учавствовать";

    public SendMessage response(Message message) {
        return new SendMessage(message.getChatId(), TEXT);
    }
}
