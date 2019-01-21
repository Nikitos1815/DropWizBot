import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;


public class BotApplication extends Application<BotConfig> {
    public static void main(String[] args) throws Exception {
        new BotApplication().run(args);

    }
    @Override
    public void initialize(Bootstrap<BotConfig> bootstrap) {

    }

    @Override
    public void run(BotConfig configuration,
                    Environment environment) {
        final BotResource resource = new BotResource();
        environment.jersey().register(resource);

        ApiContextInitializer.init();

        MongoClient client = new MongoClient("localhost", 27017);
        MongoDatabase database = client.getDatabase("userdb_queries");
        UserDB users = new UserDB(database);
        BotFunctions SetUp = new BotFunctions(users);
        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            botsApi.registerBot(new Bot(users,SetUp));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}



