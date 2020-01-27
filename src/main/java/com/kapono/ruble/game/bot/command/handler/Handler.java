package com.kapono.ruble.game.bot.command.handler;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public abstract class Handler {

    protected final Message message;

    protected Handler(Message message) {
        this.message = message;
    }

    public abstract SendMessage response();
}
