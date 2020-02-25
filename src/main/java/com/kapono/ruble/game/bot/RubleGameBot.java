package com.kapono.ruble.game.bot;

import com.kapono.ruble.game.bot.command.handler.CommandService;
import com.kapono.ruble.game.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RubleGameBot extends TelegramLongPollingBot {

    private final UserService userService;
    private final CommandService commandService;

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
                .map(curText -> commandService.getResponse(update, curText))
                .ifPresent(this::sendMessage);
        if (update.hasCallbackQuery()) {
            String data = update.getCallbackQuery().getData();
            Long chatId = update.getCallbackQuery().getMessage().getChatId();
            Integer messageId = update.getCallbackQuery().getMessage().getMessageId();
            if ("pay_ok".equals(data)) {
                Integer userId = update.getCallbackQuery().getFrom().getId();
                userService.addNewUser(userId);
                String answer = "Вы учавствуете!";
                EditMessageText new_message = new EditMessageText()
                        .setChatId(chatId)
                        .setMessageId(messageId)
                        .setText(answer);
                sendEditMessage(new_message);
            }
            if ("pay_no".equals(data)) {
                String answer = "Вы не учавствуете!";
                EditMessageText new_message = new EditMessageText()
                        .setChatId(chatId)
                        .setMessageId(messageId)
                        .setText(answer);
                sendEditMessage(new_message);
            }
        }
    }

    private void sendEditMessage(EditMessageText new_message) {
        try {
            execute(new_message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
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
