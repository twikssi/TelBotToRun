package by.Andrey.TelegramEnglishBot.data.dataFromWebSite;

import by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.InformationFromWebSite.DataFromWebSite;
import org.junit.Test;

public class DataFromWebSiteTest {


//    @Test
//    public void pullOutMoreExamplesFromWebSiteNew() {
//        DataFromWebSite fromWebSite = new DataFromWebSite("settle");
//        List<String> result = (fromWebSite.pullOutPartOfSpeechFromWebSiteNew());
//        result.forEach(System.out::println);
//        System.out.println(result.size());
//    }
//
//    @Test
//    public void pullOutTranscriptionFromWebSite() {
//        DataFromWebSite fromWebSite = new DataFromWebSite("catch up");
//        List<String> result = (fromWebSite.pullOutMeaningsFromWebSiteNew());
//
//        result.forEach(System.out::println);
//        System.out.println(result.size());
//    }
//
//    @Test
//    public void pullOutExamplesFromWebSiteNew() {
//        DataFromWebSite fromWebSite = new DataFromWebSite("frighten");
//        List<List<String>> result = (fromWebSite.pullOutExamplesFromWebSiteNew());
//
//        result.forEach(System.out::println);
//        System.out.println(result.size());
//    }

    @Test
    public void addWordVocabularyInList() {
        DataFromWebSite fromWebSite = new DataFromWebSite("frighten");
        fromWebSite.addWordVocabularyInList();

        fromWebSite.pullOutMoreExamplesFromWebSiteNew().forEach(System.out::println);
        fromWebSite.getWordVocabularyList().forEach(System.out::println);

        fromWebSite.pullOutSuggestionsWords().forEach(System.out::println);

    }

}