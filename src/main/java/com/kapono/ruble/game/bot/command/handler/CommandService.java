package com.kapono.ruble.game.bot.command.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@RequiredArgsConstructor
public class CommandService {

    private final PrizeService prizeService;
    private final PayService payService;
    private final StartService startService;
    private final StatusService statusService;

    public SendMessage getResponse(Update update, String text) {
        if (text.startsWith("/start")) {
            return startService.response(update.getMessage());
        }
        if (text.startsWith("/prize")) {
            return prizeService.response(update.getMessage());
        }
        if (text.startsWith("/pay")) {
            return payService.response(update.getMessage());
        }
        if (text.startsWith("/status")) {
            return statusService.response(update.getMessage());
        }
        return new SendMessage(update.getMessage().getChatId(), "Используй команды");
    }

}
