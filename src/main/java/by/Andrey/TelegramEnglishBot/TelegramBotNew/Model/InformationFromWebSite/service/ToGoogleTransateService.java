package by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.InformationFromWebSite.service;

import by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.Bean.WordVocabulary;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.InformationFromWebSite.googleTranslate.ToGoogletranslate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ToGoogleTransateService {

    @Autowired
    private ToGoogletranslate toGoogletranslate;

    private String getOneStringOfList(List<String> exampleList){
        String text = "";

        for (String example: exampleList){
            text = text + example;
        }
        return text;
    }

    public String getGoogleUrl(List<String> examples){
        return toGoogletranslate.getUrlGoogleWithCorrectText(getOneStringOfList(examples));
    }

    public String checkAndAddPoint(String example){
        if (example.contains(".") || example.contains("?") || example.contains("!")) return example;
        else {
            example = example + ".";
            return example;
        }
    }

    public String getGoogleUrlWithVocabulary(List<WordVocabulary> wordVocabularyList){
        String allExamples = "";
        for (WordVocabulary wordVocabulary: wordVocabularyList){
            for (String example: wordVocabulary.getExamples()){
                allExamples = allExamples + checkAndAddPoint(example);
            }
          //  allExamples = allExamples + getOneStringOfList(wordVocabulary.getExamples());
        }
        return toGoogletranslate.getUrlGoogleWithCorrectText(allExamples);
    }
}
