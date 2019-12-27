package com.kapono.ruble.game.bot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class RubleGameBot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            SendMessage message = new SendMessage(
                    update.getMessage().getChatId(),
                    update.getMessage().getText()
            );
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "RubleGameBot";
    }

    @Override
    public String getBotToken() {
        return "1008769670:AAFNqIuMQm1JZxHQ1zsxCZZNydq8Hqz9rng";
    }
}
