package by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.process;

import by.Andrey.TelegramEnglishBot.BorisevichBot;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.Bean.User;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.InformationFromWebSite.service.DataFromWebSiteService;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.dao.service.UserRepositoriesService;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.View.SetupMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class ProcessWordToDelete {
    @Autowired
    private UserRepositoriesService userRepositoriesService;
    @Autowired
    private DataFromWebSiteService dataFromWebSiteService;
    @Autowired
    private BorisevichBot borisevichBot;
    @Autowired
    private SetupMessage setupMessage;

    public void run(Message message, User user) {
        String userWord = message.getText().toLowerCase();
        long chatid = message.getChatId();

        userRepositoriesService.deleteWord(userWord, chatid);
    }
}
