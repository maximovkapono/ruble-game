package com.kapono.ruble.game.bot;

import com.kapono.ruble.game.bot.command.handler.CommandFactory;
import com.kapono.ruble.game.bot.command.handler.Handler;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Optional;

@Component
public class RubleGameBot extends TelegramLongPollingBot {

    @Value("${bot.token}")
    private String token;

    @Value("${bot.username}")
    private String username;

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Optional.ofNullable(update)
                .filter(Update::hasMessage)
                .map(Update::getMessage)
                .map(Message::getText)
                .map(String::trim)
                .filter(Strings::isNotEmpty)
                .map(curText -> CommandFactory.getCommandHandler(update, curText))
                .map(Handler::response)
                .ifPresent(this::sendMessage);
    }

    private void sendMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
