package by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.InformationFromWebSite;

import by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.Bean.WordVocabulary;

import lombok.Data;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class DataFromWebSite {
    private Document doc;
    private Document docSuggestingsWords;
    private String word;
    private List<WordVocabulary> wordVocabularyList = new ArrayList<>();

    public static final String dictinary = "https://dictionary.cambridge.org/dictionary/english/";
    public static final String suggestingsWordsUrl = "https://dictionary.cambridge.org/spellcheck/english/?q=";

    public DataFromWebSite(String wordName) {
        try {
            this.doc = Jsoup.connect(dictinary + wordName).get();
            this.docSuggestingsWords = Jsoup.connect(suggestingsWordsUrl + wordName).get();
            word = wordName;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public String pullOutPartOfSpeechFromWebSite() {
//        Elements element = doc.getElementsByClass("pos dpos");
//        return Jsoup.parse(element.toString()).text();
//    }
//
//    public String pullOutTranscriptionFromWebSite() {
//        Elements element = doc.getElementsByClass("ipa dipa lpr-2 lpl-1");
//        return Jsoup.parse(element.toString()).text();
//    }
//
//    public String pullOutMeaningFromWebSite() {
//        Elements element = doc.getElementsByClass("def ddef_d db");
//        return Jsoup.parse(element.toString()).text();
//    }
//
//    public String pullOutExampleFromWebSite() {
//        Elements element = doc.getElementsByClass("examp dexamp");
//        return Jsoup.parse(element.toString()).text();
//    }

//    public String pullOutMoreExamplesFromWebSite() {
//        Elements element = doc.getElementsByClass("eg dexamp hax");
//        return Jsoup.parse(element.toString()).text();
//    }
//
//    public List<String> getListNoFormatFieldOfWord() {
//        List<String> listNoFormatFieldsOfWord = new ArrayList<>();
//        listNoFormatFieldsOfWord.add(word);
//        listNoFormatFieldsOfWord.add(pullOutPartOfSpeechFromWebSite());
//        listNoFormatFieldsOfWord.add(pullOutTranscriptionFromWebSite());
//        listNoFormatFieldsOfWord.add(pullOutMeaningFromWebSite());
//        listNoFormatFieldsOfWord.add(pullOutExampleFromWebSite());
//        return listNoFormatFieldsOfWord;
//    }


    // new brand features:

    public String pullOutTranscriptionFromWebSiteNew() {
        Elements element = doc.getElementsByClass("ipa dipa lpr-2 lpl-1");
        if (element.isEmpty()) {
            return "";
        } else {
            return Jsoup.parse(element.first().toString()).text();
        }
    }

    public List<String> pullOutMoreExamplesFromWebSiteNew() {
        Elements element = doc.getElementsByClass("eg dexamp hax");
        return element.stream().map(a ->Jsoup.parse(a.toString()).text()).collect(Collectors.toList());
    }

    public List<String> pullOutPartOfSpeechFromWebSiteNew() {
        List<Integer> sizeMeanList = new ArrayList<>();
        List<String> partOfList = new ArrayList<>();
        List<String> partOfSpeechList = new ArrayList<>();

        Elements words = doc.getElementsByClass("pos dpos");
        Elements bodies = doc.getElementsByClass("pos-body");

        if (bodies.size() == 0 ) {
            bodies = doc.getElementsByClass("pv-block");
        }

        for (Element body : bodies) {
            int sizeMean = body.getElementsByClass("def ddef_d db").size();
            sizeMeanList.add(sizeMean);
        }

        for (Element word : words) {
            String partOfString = Jsoup.parse(word.toString()).text();
            partOfList.add(partOfString);
        }


        int j = 0;
        for (Integer sizeMean : sizeMeanList) {
            for (int i = 0; i < sizeMean; i++) {
                partOfSpeechList.add(partOfList.get(j));
            }
            j++;
        }


        //rapire
        if (partOfList.size() != 0){
            for (int i=0; i < 20; i++){
                partOfSpeechList.add(partOfList.get(0));
            }
        }

        return partOfSpeechList;
    }

    public List<String> pullOutMeaningsFromWebSiteNew() {

        List<String> meaningsList = new ArrayList<>();

        Elements meaningsAndEx = doc.getElementsByClass("def-block ddef_block ");

        for (Element meaning : meaningsAndEx) {
            String mean = Jsoup.parse(meaning.getElementsByClass("def ddef_d db").toString()).text();
            meaningsList.add(mean);
        }
        return meaningsList;
    }

    public List<List<String>> pullOutExamplesFromWebSiteNew() {
        List<List<String>> exampleList = new ArrayList<>();

        Elements meaningsAndEx = doc.getElementsByClass("def-block ddef_block ");

        for (Element meaning : meaningsAndEx) {
           List<String> text = new ArrayList<>();

            Elements examples = meaning.getElementsByClass("examp dexamp");

            for (Element example : examples) {
                text.add(Jsoup.parse(example.toString()).text());
            }
            exampleList.add(text);
        }

        return exampleList;
    }

    //If search doesn't get results.
    public List<String> pullOutSuggestionsWords(){

        List<String> suggestinonsWords = new ArrayList<>();

        Elements suggestinonsDiv = docSuggestingsWords.getElementsByTag("hfl-s lt2b lmt-10 lmb-25 lp-s_r-20");
     //    getElementsByClass("hul-u");
        for (Element element: suggestinonsDiv){
            suggestinonsWords.add(Jsoup.parse(element.getElementsByClass("lbt lp-5 lpl-20").toString()).text());
        }

        return suggestinonsWords;
    }

    public void addWordVocabularyInList () {
            for (int i = 0; i<pullOutMeaningsFromWebSiteNew().size() ; i++){
                wordVocabularyList.add(WordVocabulary.builder()
                        .name(word)
                        .partsOfSpeech(pullOutPartOfSpeechFromWebSiteNew().get(i))
                        .transcription(pullOutTranscriptionFromWebSiteNew())
                        .meaning(pullOutMeaningsFromWebSiteNew().get(i))
                        .examples(pullOutExamplesFromWebSiteNew().get(i))
                        //.moreExample(pullOutMoreExamplesFromWebSiteNew())
                        .build());
            }
    }
}
