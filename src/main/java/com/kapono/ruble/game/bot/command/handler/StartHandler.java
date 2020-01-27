package com.kapono.ruble.game.bot.command.handler;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class StartHandler extends Handler {

    public static final String TEXT = "В этом боте ты можешь выиграть деньги!";

    public StartHandler(Message message) {
        super(message);
    }

    @Override
    public SendMessage response() {
        return new SendMessage(message.getChatId(), TEXT);
    }
}
