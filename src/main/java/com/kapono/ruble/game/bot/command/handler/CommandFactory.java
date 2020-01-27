package com.kapono.ruble.game.bot.command.handler;

import org.telegram.telegrambots.meta.api.objects.Update;

public class CommandFactory {

    public static Handler getCommandHandler(Update update, String text) {
        if (text.startsWith("/start")) {
            return new StartHandler(update.getMessage());
        }
        if (text.startsWith("/prize")) {
            return new PrizeHandler(update.getMessage());
        }
        if (text.startsWith("/pay")) {
            return new PayHandler(update.getMessage());
        }
        if (text.startsWith("/status")) {
            return new StatusHandler(update.getMessage());
        }
        return new DefaultHandler(update.getMessage());
    }

}
