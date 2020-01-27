package com.kapono.ruble.game.bot.command.handler;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class StatusHandler extends Handler {

    public static final String TEXT = "Ты пока не учавствуешь в розыгрыше";

    public StatusHandler(Message message) {
        super(message);
    }

    @Override
    public SendMessage response() {
        return new SendMessage(message.getChatId(), TEXT);
    }
}
