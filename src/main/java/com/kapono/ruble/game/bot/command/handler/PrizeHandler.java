package com.kapono.ruble.game.bot.command.handler;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class PrizeHandler extends Handler {

    public static final String TEXT = "Текущий выигрышь 400р!";

    public PrizeHandler(Message message) {
        super(message);
    }

    @Override
    public SendMessage response() {
        return new SendMessage(message.getChatId(), TEXT);
    }
}
