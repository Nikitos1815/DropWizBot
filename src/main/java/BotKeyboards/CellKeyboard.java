package BotKeyboards;

import BotKeyboards.KeyboardType;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class CellKeyboard implements KeyboardType {
    @Override
    public SendMessage keyBoard(long chat_id) {
        SendMessage message = new SendMessage() // Create a message object object
                .setChatId(chat_id)
                .setText("Добро пожаловать!");
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline2 = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton().setText("ФИО").setCallbackData("FIO"));
        rowInline.add(new InlineKeyboardButton().setText("Номер телефона").setCallbackData("PhoneNumber"));

        rowInline2.add(new InlineKeyboardButton().setText("Адрес").setCallbackData("HomeAddress"));
        rowInline2.add(new InlineKeyboardButton().setText("E-mail").setCallbackData("E-mail"));

        // Set the keyboard to the markup
        rowsInline.add(rowInline);
        rowsInline.add(rowInline2);
        // Add it to the message
        markupInline.setKeyboard(rowsInline);
        message.setReplyMarkup(markupInline);

        return message;
    }
}
