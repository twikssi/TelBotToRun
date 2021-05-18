package by.Andrey.TelegramEnglishBot.TelegramBotNew.Controller;

import by.Andrey.TelegramEnglishBot.BorisevichBot;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.Bean.User;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.constants.BotState;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.dao.service.UserRepositoriesService;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.process.ProcessWordToAdd;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.View.MenuConstants.Menu;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.View.SetupMessage;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.View.keyboards.Keyboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class MenuWriteWordToAddController {
    @Autowired
    private BorisevichBot borisevichBot;
    @Autowired
    private Keyboard keyboard;
    @Autowired
    private SetupMessage setupMessage;
    @Autowired
    private UserRepositoriesService userRepositoriesService;
    @Autowired
    private ProcessWordToAdd processWordToAdd;

    public void runMenuWriteWord(Message message, User user) {
        String text = message.getText().toLowerCase();
        long chatid = message.getChatId();

        if (text.equals(Menu.menuCommandBack)) {
            user.setBotStep(BotState.SETUP);
            userRepositoriesService.saveUser(user);

            borisevichBot.sendMessage(
                    setupMessage.setupMessageWithKeyboard(
                            message.getChatId(),
                            keyboard.createMenuSetupWords(),
                            Menu.CHOOSE_COMMAND
                    )
            );
        } else {

            if (userRepositoriesService.isWordExist(text, chatid)) {
                user.setBotStep(BotState.INPUT_WORD_TO_ADD);
                userRepositoriesService.saveUser(user);
                userRepositoriesService.restoreWord(text, chatid);



                borisevichBot.sendMessage(
                        setupMessage.setupMessageWithKeyboard(
                                message.getChatId(),
                                keyboard.createMenuBack(),
                                "the word is in your vocabulary. Try again with new word"
                        )
                );
            } else {
                user.setBotStep(BotState.SETUP);
                processWordToAdd.run(message, user);

                borisevichBot.sendMessage(
                        setupMessage.setupMessageWithKeyboard(
                                message.getChatId(),
                                keyboard.createMenuSetupWords(),
                                Menu.CHOOSE_COMMAND
                        )
                );
            }

        }
    }
}

