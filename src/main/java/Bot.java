import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;


public class Bot extends TelegramLongPollingBot {
    private UserDB db;
    private BotFunctions setup;
    private String username;
    private long id;
    private long chatId;

    public Bot(UserDB db, BotFunctions setup) {
        this.setup = setup;
        this.db = db;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            long chatId = update.getMessage().getChatId();
            username = update.getMessage().getChat().getUserName();
            id = update.getMessage().getChat().getId();

            db.registration(username, id);

            if (update.getMessage().getText().equals("/start")) {
                SendMessage message2 = Keyboards.Keyboard(chatId);
                try {
                    execute(message2); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

            } else {
                int counter = db.flagCheck(id);
                SendMessage message = setup.switchChoice(counter,chatId,id);
                try{
                    execute(message);
                }catch (TelegramApiException e){
                    e.printStackTrace();
                }
            }
        } else if (update.hasCallbackQuery()) {
            Long id = update.getCallbackQuery().getMessage().getChat().getId();

            SendMessage message = BotFunctions.Callback(update);
            setup.dbCheck(message,id);
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        // Return Bot username
        // If Bot username is @MyAmazingBot, it must return 'proswimbot'
        return "proswimbot";
    }

    @Override
    public String getBotToken() {
        // Return Bot token from BotFather
        return "624724125:AAEZKWtsxN5AJUhXxg6AEIqpjWxopKCo268";
    }

}

