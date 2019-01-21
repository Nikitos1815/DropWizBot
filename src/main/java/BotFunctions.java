import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class BotFunctions {
    private UserDB db;
    private SendMessage message;
    public BotFunctions(UserDB db){
        this.db = db;
    }


    public static SendMessage Callback(Update update){
        SendMessage new_message = new SendMessage();
        String call_data = update.getCallbackQuery().getData();
        long chat_id = update.getCallbackQuery().getMessage().getChatId();
        if (call_data.equals("FIO")) {

            new_message.setChatId(chat_id).setText("Введите ФИО");

        } else if (call_data.equals("PhoneNumber")) {

            new_message
                    .setChatId(chat_id)
                    .setText("Введите номер телефона");

        } else if (call_data.equals("HomeAddress")) {

            new_message
                    .setChatId(chat_id)
                    .setText("Введите адрес");
            return new_message;
        } else if (call_data.equals("E-mail")) {
            new_message
                    .setChatId(chat_id)
                    .setText("Введите E-mail");

        }

        return new_message;
    }

    public  SendMessage switchChoice(int counter,long chatId, long id){
        switch (counter) {
            case 1:
                message = new SendMessage() // Create a message object object
                        .setChatId(chatId)
                        .setText("Nice shoot!");
                db.flagDelete(id);

                break;
            case 2:
                 message = new SendMessage() // Create a message object object
                        .setChatId(chatId)
                        .setText("Here will be phone number");
                db.flagDelete(id);

                break;
            case 3:
                 message = new SendMessage() // Create a message object object
                        .setChatId(chatId)
                        .setText("Here will be Address");
                db.flagDelete(id);

                break;

            case 4:
                 message = new SendMessage() // Create a message object object
                        .setChatId(chatId)
                        .setText("Here will be E-mail");
                db.flagDelete(id);

                break;
            case 0:
                message = new SendMessage()
                        .setChatId(chatId)
                        .setText("Введите /start или выберите пункт меню" + "\n");

                break;

        }
        return message;
    }
    public void dbCheck(SendMessage message, long id){
        switch (message.getText()) {
            case "Введите ФИО":
                db.query("FIO", id);
                break;
            case "Введите номер телефона":
                db.query("Phone", id);
                break;
            case "Введите адрес":
                db.query("Address", id);
                break;
            case "Введите E-mail":
                db.query("Mail", id);
                break;
        }
    }
}
