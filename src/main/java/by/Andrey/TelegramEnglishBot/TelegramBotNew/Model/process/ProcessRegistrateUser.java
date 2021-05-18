package by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.process;

import by.Andrey.TelegramEnglishBot.BorisevichBot;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.Bean.User;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.constants.BotState;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.dao.service.UserRepositoriesService;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.View.keyboards.Keyboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class ProcessRegistrateUser {

    @Autowired
    private UserRepositoriesService userRepositoriesService;
    @Autowired
    private Keyboard keyboard;
    @Autowired
    private BorisevichBot borisevichBot;

    public void run(Message message, User user){
        user.setChatId(message.getChatId());
        user.setBotStep(BotState.MAIN_MENU);
        userRepositoriesService.saveUser(user);

        borisevichBot.sendMessage(new SendMessage().
                setChatId(message.getChatId()).setText("Hi, You can start to learn words!").
                setReplyMarkup(keyboard.createMainMenu()));
    }
}
