package by.Andrey.TelegramEnglishBot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
@EnableAsync
public class TelegramEnglishBotApp {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(TelegramEnglishBotApp.class, args);

    }
}
