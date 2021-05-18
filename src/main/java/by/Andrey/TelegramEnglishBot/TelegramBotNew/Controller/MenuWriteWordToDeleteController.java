package by.Andrey.TelegramEnglishBot.TelegramBotNew.Controller;

import by.Andrey.TelegramEnglishBot.BorisevichBot;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.Bean.User;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.constants.BotState;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.dao.service.UserRepositoriesService;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.process.ProcessWordToAdd;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.process.ProcessWordToDelete;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.View.MenuConstants.Menu;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.View.SetupMessage;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.View.keyboards.Keyboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class MenuWriteWordToDeleteController {
    @Autowired
    private BorisevichBot borisevichBot;
    @Autowired
    private Keyboard keyboard;
    @Autowired
    private SetupMessage setupMessage;
    @Autowired
    private UserRepositoriesService userRepositoriesService;
    @Autowired
    private ProcessWordToDelete processWordToDelete;

    public void runMenuWriteWordToDelete(Message message, User user) {
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
                user.setBotStep(BotState.SETUP);
                userRepositoriesService.saveUser(user);

                processWordToDelete.run(message, user);

                borisevichBot.sendMessage(
                        setupMessage.setupMessageWithKeyboard(
                                message.getChatId(),
                                keyboard.createMenuSetupWords(),
                                "The word has been deleted!"
                        )
                );
            } else {
                user.setBotStep(BotState.INPUT_WORD_TO_DELETE);
                userRepositoriesService.saveUser(user);

                borisevichBot.sendMessage(
                        setupMessage.setupMessageWithKeyboard(
                                message.getChatId(),
                                keyboard.createMenuBack(),
                                "There is no the word from your vocabulary"
                        )
                );
            }

        }
    }
}
