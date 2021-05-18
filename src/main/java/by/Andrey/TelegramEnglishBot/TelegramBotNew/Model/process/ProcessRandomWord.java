package by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.process;

import by.Andrey.TelegramEnglishBot.BorisevichBot;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.Bean.User;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.Bean.Word;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.Bean.WordVocabulary;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.InformationFromWebSite.DataFromWebSite;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.InformationFromWebSite.googleTranslate.ToGoogletranslate;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.InformationFromWebSite.service.DataFromWebSiteService;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.InformationFromWebSite.service.ToGoogleTransateService;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.dao.service.UserRepositoriesService;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.View.DrawMessage;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.View.SetupMessage;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.View.keyboards.Keyboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Collections;
import java.util.List;

@Component
public class ProcessRandomWord {
    @Autowired
    private BorisevichBot borisevichBot;
    @Autowired
    private Keyboard keyboard;
    @Autowired
    private SetupMessage setupMessage;
    @Autowired
    private UserRepositoriesService userRepositoriesService;
    @Autowired
    private DataFromWebSiteService dataFromWebSiteService;
    @Autowired
    private DrawMessage drawMessage;
    @Autowired
    private ToGoogleTransateService toGoogleTransateService;



    public void run(User user) {
        long chatId = user.getChatId();

        List<Word> words = userRepositoriesService.getListWords(chatId);
        if (words.size() != 0) {
            Collections.shuffle(words);

            Word word = words.get(0);

            List<WordVocabulary> vocabularyList = dataFromWebSiteService.getAllWordVocabulary(word.getName());
            List<String> moreExamples = dataFromWebSiteService.getAllMoreExamples(word.getName());

            borisevichBot.sendMessage(setupMessage.setupMessage(chatId,
                    drawMessage.getDoYouMemberIt(word.getName())));

         for(WordVocabulary wordVocabulary: vocabularyList){
             borisevichBot.sendMessage(setupMessage.setupMessage(chatId,
                     drawMessage.getAmazingView(wordVocabulary)));
         }
            borisevichBot.sendMessage(setupMessage.setupMessageWithKeyboard(chatId,
                    keyboard.InlineKeyBoardMessageLink(toGoogleTransateService.getGoogleUrlWithVocabulary(vocabularyList)),
                    "Press button to listening examples with google translate"));


         if (moreExamples.size() != 0){
             borisevichBot.sendMessage(setupMessage.setupMessageWithKeyboard(chatId,
                     keyboard.InlineKeyBoardMessageLink(toGoogleTransateService.getGoogleUrl(moreExamples)),
                     "More Examples: \n" + drawMessage.getAmazingViewForMoreExamples(moreExamples)));
         }

            borisevichBot.sendMessage(setupMessage.setupMessageWithKeyboard(chatId,
                    keyboard.InlineKeyBoardMessageLearn(word.getName()),
                    "Press the button for learned the word!"));

        } else {
            borisevichBot.sendMessage(setupMessage.setupMessage(chatId, "You have no words"));
        }
    }

}
