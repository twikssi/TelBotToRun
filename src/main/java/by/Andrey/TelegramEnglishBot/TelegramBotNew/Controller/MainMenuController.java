package by.Andrey.TelegramEnglishBot.TelegramBotNew.Controller;

import by.Andrey.TelegramEnglishBot.BorisevichBot;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.Bean.User;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.constants.BotState;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.dao.service.UserRepositoriesService;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.process.ProcessRandomWord;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.View.DrawMessage;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.View.MenuConstants.Menu;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.View.SetupMessage;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.View.keyboards.Keyboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;


@Component
public class MainMenuController {
    @Autowired
    private BorisevichBot borisevichBot;
    @Autowired
    private Keyboard keyboard;
    @Autowired
    private SetupMessage setupMessage;
    @Autowired
    private UserRepositoriesService userRepositoriesService;
    @Autowired
    private ProcessRandomWord processRandomWord;
    @Autowired
    private DrawMessage drawMessage;

    public void runMainMenu(Message message, User user) {
        String text = message.getText();
        long chatid = message.getChatId();

        if (text.equals(Menu.mainMenuRandom)) {

            processRandomWord.run(user);

        } else if (text.equals(Menu.mainMenuStatistic)) {

            String statisticWords = drawMessage.getAmazingStatistics(userRepositoriesService.getListWords(chatid));
            borisevichBot.sendMessage(setupMessage.setupMessage(message.getChatId(),"Words: \n" + statisticWords));
            String statisticDeleteWords = drawMessage.getAmazingStatistics(userRepositoriesService.getListDeleteWords(chatid));
            borisevichBot.sendMessage(setupMessage.setupMessage(message.getChatId(), "Deleted words: \n" + statisticDeleteWords));

        }  else if (text.equals(Menu.mainMenuAddWord)) {
            user.setBotStep(BotState.SETUP);
            userRepositoriesService.saveUser(user);

            borisevichBot.sendMessage(setupMessage.setupMessageWithKeyboard(
                    message.getChatId(),
                    keyboard.createMenuSetupWords(),
                    Menu.CHOOSE_COMMAND));

        } else {
//            System.out.println(message.getText());
//            borisevichBot.sendMessage(setupMessage.setupMessage(
//                    message.getChatId(),
//                    "✅ \uD83D\uDDD1"));
        }

    }
}
