package by.Andrey.TelegramEnglishBot.TelegramBotNew.Controller;

import by.Andrey.TelegramEnglishBot.BorisevichBot;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.Bean.User;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.Bean.Word;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.constants.BotState;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.dao.service.UserRepositoriesService;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.process.ProcessRegistrateUser;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.View.SetupMessage;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.View.keyboards.Keyboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;


@Component
public class MainController {

    @Autowired
    public UserRepositoriesService userRepositoriesService;
    private long chatId;
    @Autowired
    private ProcessRegistrateUser registrateUser;
    @Autowired
    private MainMenuController mainMenuController;
    @Autowired
    private MenuSetupController menuSetupController;
    @Autowired
    private BorisevichBot borisevichBot;
    @Autowired
    private Keyboard keyboard;
    @Autowired
    private SetupMessage setupMessage;
    @Autowired
    private MenuWriteWordToAddController menuWriteWordToAddController;
    @Autowired
    private MenuWriteWordToDeleteController menuWriteWordToDeleteController;

    public void runMessage(Message message) {
        chatId = message.getChatId();
        User user = userRepositoriesService.findByChatId(chatId);
        if (user.getBotStep() == null) {
            registrateUser.run(message, user);
        }

        if (message.getText().equals("/start")) {
            user.setBotStep(BotState.MAIN_MENU);
            userRepositoriesService.saveUser(user);

            borisevichBot.sendMessage(setupMessage.setupMessageWithKeyboard(
                    message.getChatId(),
                    keyboard.createMainMenu(),
                    "Hi, You can carry on to learn words!"));

        }

        switch (user.getBotStep()) {
            case BotState.MAIN_MENU:
                mainMenuController.runMainMenu(message, user);
                break;
            case BotState.SETUP:
                menuSetupController.runSetupMenu(message, user);
                break;
            case BotState.INPUT_WORD_TO_ADD:
                menuWriteWordToAddController.runMenuWriteWord(message, user);
                break;
            case BotState.INPUT_WORD_TO_DELETE:
                menuWriteWordToDeleteController.runMenuWriteWordToDelete(message, user);
                break;
            default:

                break;
        }
    }

    public void runCallback(CallbackQuery callbackQuery) {
        User user = userRepositoriesService.findByChatId(chatId);

        List<Word> listWords = user.getWordList();

        for (Word word : listWords) {
            if (callbackQuery.getData().equals(word.getName())) {
                word.setNumberOfRepetitions(word.getNumberOfRepetitions() + 1);


                borisevichBot.editMessage(new EditMessageText().
                        setChatId(chatId).
                        setMessageId(callbackQuery.getMessage().getMessageId()).
                        setText("You have learned the word!")
                );
            }
        }
        userRepositoriesService.saveUser(user);
    }
}
