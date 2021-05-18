package by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.InformationFromWebSite.googleTranslate;

import org.springframework.stereotype.Component;

@Component
public class ToGoogletranslate {
    public static final String urlGoogle = "https://translate.google.by/?hl=ru&sl=en&tl=es&text=";

    public String replaceAllspaceOn20Percent(String text){
        return text.replace(" ", "%20");
    }

    public String replaceIncorrectSymbals(String textInccorrect){
        String correctText = textInccorrect;
        correctText = correctText.replace("\"", ". ");
        correctText = correctText.replace("/", ". ");
        correctText = correctText.replace("'", "%27");
        correctText = correctText.replace("`", "%27");
        correctText = correctText.replace("’", "%27");
        return correctText;
    }

    public String getUrlGoogleWithCorrectText(String correctText){
        return urlGoogle.concat(replaceAllspaceOn20Percent(replaceIncorrectSymbals(correctText)));
    }
}
