package com.kapono.ruble.game.bot.command.handler;

import com.kapono.ruble.game.entity.GameEntity;
import com.kapono.ruble.game.repository.GameRepository;
import com.kapono.ruble.game.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PayService {

    private final UsersRepository usersRepository;
    private final GameRepository gameRepository;

    public SendMessage response(Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        Integer userId = message.getFrom().getId();
        GameEntity currentGame = gameRepository.findCurrentGame();
        if (currentGame != null) {
            if (!usersRepository.existsByUserIdAndGame(userId, currentGame)) {
                InlineKeyboardMarkup keyboardMarkup = getInlineKeyboardMarkup();
                sendMessage.setReplyMarkup(keyboardMarkup);
                sendMessage.setText("Участие стоит 100 рублей, платим?");
            } else {
                sendMessage.setText("Вы уже учавствуете в розыгрыше");
            }
        } else {
            sendMessage.setText("На данный момент розыгрыш не запущен");
        }
        return sendMessage;
    }

    private InlineKeyboardMarkup getInlineKeyboardMarkup() {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        InlineKeyboardButton payBtnOk = new InlineKeyboardButton("Ok").setCallbackData("pay_ok");
        InlineKeyboardButton payBtnNo = new InlineKeyboardButton("No").setCallbackData("pay_no");
        rowInline.add(payBtnOk);
        rowInline.add(payBtnNo);
        rowsInline.add(rowInline);
        keyboardMarkup.setKeyboard(rowsInline);
        return keyboardMarkup;
    }
}
