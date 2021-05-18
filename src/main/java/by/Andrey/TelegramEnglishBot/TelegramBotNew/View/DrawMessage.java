package by.Andrey.TelegramEnglishBot.TelegramBotNew.View;

import by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.Bean.Word;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.Bean.WordVocabulary;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.View.Emoji.Emoji;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DrawMessage {

    public String drawListWords(List<Word> wordList){
        String message = "";
        for (Word word: wordList){
            message =  message + Emoji.CLEVER + word.getName() + "\n";
        }
        return message;
    }

    public String getDoYouMemberIt (String name){
        String firstViewWithQuestions = "'" + name +"'" + " - " +
                Emoji.QUESTION_AND.toString() +
                Emoji.QUESTION_AND.toString() +
                Emoji.QUESTION_AND.toString() +
                Emoji.QUESTION_AND.toString() +
                Emoji.QUESTION_AND.toString() + "\n" +
                Emoji.QUESTION_AND.toString() + "\n" +
                Emoji.QUESTION_AND.toString() + "\n" +
                Emoji.QUESTION_AND.toString() + "\n" +
                Emoji.QUESTION_AND.toString() + "\n" +
                Emoji.QUESTION_AND.toString() + "\n" +
                Emoji.QUESTION_AND.toString() + "\n" +
                Emoji.QUESTION_AND.toString()  +
                "Do you 'member it" +  Emoji.QUESTION_AND.toString() + "\n\n";
        return firstViewWithQuestions;
    }

    private String getBeginAndEnd(){
        String beginAndEnd = "\t" + Emoji.TILDA.toString() +
                Emoji.TILDA.toString() +
                Emoji.TILDA.toString() +
                Emoji.TILDA.toString()  +
                Emoji.TILDA.toString() +
                Emoji.TILDA.toString() +
                Emoji.PIN.toString() +
                Emoji.TILDA.toString() +
                Emoji.TILDA.toString() +
                Emoji.TILDA.toString() +
                Emoji.TILDA.toString() +
                Emoji.TILDA.toString() +
                Emoji.TILDA.toString() +
                "\n";
        return beginAndEnd;
    }

    public String getAmazingView(WordVocabulary wordVocabulary){

        String view =Emoji.SUNFLOWER +
                wordVocabulary.getName() +
                        " ("+ wordVocabulary.getPartsOfSpeech().toString().toLowerCase() + ") - " +
                        wordVocabulary.getTranscription() + " - " +
                        wordVocabulary.getMeaning() + "\n";
       // String[] exampleParts = breakIntoPeacesFieldExample();
        for (String word: wordVocabulary.getExamples()){
            view = view.concat(Emoji.CLEVER.toString() + " " + word + "\n");
        }
        view =  getBeginAndEnd() + view + getBeginAndEnd();
        return view;
    }

    public String getAmazingViewForMoreExamples(List<String> moreExamplesList){
        String view = "";
        for (String word: moreExamplesList){
            view = view.concat(Emoji.CLEVER.toString() + " " + word + "\n");
        }
        view =  getBeginAndEnd() + view + getBeginAndEnd();
        return view;
    }

    public String getAmazingStatistics(List<Word> wordList){
        String textStatistic = "";
        for(Word word: wordList){
            textStatistic = textStatistic
                    + "'"
                    + word.getName()
                    + "' has been repeated "
                    + word.getNumberOfRepetitions()
                    +  " times."
                    + "\n";
        }
       return textStatistic;
    }
}
