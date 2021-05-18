package by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.process;

import by.Andrey.TelegramEnglishBot.BorisevichBot;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.Bean.User;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.Bean.Word;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.Bean.WordVocabulary;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.InformationFromWebSite.service.DataFromWebSiteService;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.dao.service.UserRepositoriesService;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.View.SetupMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProcessWordToAdd {

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

        List<WordVocabulary> wordVocabularyList = dataFromWebSiteService.getAllWordVocabulary(userWord);

        if (wordVocabularyList.size() == 0) {
            borisevichBot.sendMessage(
                    setupMessage.setupMessage(
                            message.getChatId(),
                            "There is no this word. Search suggestions for " + userWord
                    ));
        } else {



            Word word = new Word();
            word.setName(userWord);

            List<Word> wordList = new ArrayList<>();
            wordList.add(word);
            user.getWordList().addAll(wordList);
            userRepositoriesService.saveUser(user);

            borisevichBot.sendMessage(
                    setupMessage.setupMessage(
                            message.getChatId(),
                            "The word " + userWord + " has been added!"
                    ));
        }

    }
}
