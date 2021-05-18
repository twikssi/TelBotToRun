package by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.InformationFromWebSite.service;

import by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.Bean.WordVocabulary;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.InformationFromWebSite.DataFromWebSite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataFromWebSiteService {

    public List<WordVocabulary> getAllWordVocabulary(String word){
        DataFromWebSite dataFromWebSite = new DataFromWebSite(word);
        dataFromWebSite.addWordVocabularyInList();
        return dataFromWebSite.getWordVocabularyList();
    }

    public List<String> getAllMoreExamples(String word){
        DataFromWebSite dataFromWebSite = new DataFromWebSite(word);
        return dataFromWebSite.pullOutMoreExamplesFromWebSiteNew();
    }
}
