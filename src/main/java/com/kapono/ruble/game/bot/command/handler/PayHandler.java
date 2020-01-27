package com.kapono.ruble.game.bot.command.handler;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class PayHandler extends Handler {

    public static final String TEXT = "Участие стоит 100 рублей, платим?";

    public PayHandler(Message message) {
        super(message);
    }

    @Override
    public SendMessage response() {
        return new SendMessage(message.getChatId(), TEXT);
    }
}
