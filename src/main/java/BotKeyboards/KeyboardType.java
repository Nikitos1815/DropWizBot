package BotKeyboards;

import org.telegram.telegrambots.api.methods.send.SendMessage;

public interface KeyboardType {
      SendMessage keyBoard(long chat_id);
}
