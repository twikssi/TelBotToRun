package by.Andrey.TelegramEnglishBot;

import by.Andrey.TelegramEnglishBot.TelegramBotNew.Controller.MainController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Component
public class BorisevichBot extends TelegramLongPollingBot {

    @Autowired
    private MainController mainController;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()){
            mainController.runMessage(update.getMessage());
        } else if (update.hasCallbackQuery()){
            mainController.runCallback(update.getCallbackQuery());
        }
    }

    public void sendMessage(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    public void editMessage(EditMessageText editMessageText) {
        try {
            execute(editMessageText);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void deleteMessage(DeleteMessage deleteMessage) {
        try {
            execute(deleteMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String getBotUsername() {
        return "EnglishAndrewBot";
    }

    @Override
    public String getBotToken() {
        return "1332611656:AAESQWdBeCTWH_pdFtK2zBncACfTNWObANk";
    }

}
