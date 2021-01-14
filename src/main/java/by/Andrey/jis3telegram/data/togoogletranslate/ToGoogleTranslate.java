package by.Andrey.jis3telegram.data.togoogletranslate;



public class ToGoogleTranslate {
    public static final String urlGoogle = "https://translate.google.by/?hl=ru&sl=en&tl=es&text=";

    public static String replaceAllspaceOn20Percent(String text){
        return text.replace(" ", "%20");
    }

    public static String replaceIncorrectSymbals(String textInccorrect){
        String correctText = textInccorrect;
        correctText = correctText.replace("\"", " ");
        correctText = correctText.replace("/", " ");
        correctText = correctText.replace("'","%27");
        return correctText;
    }

    public static String getUrlGoogleWithCorrectText(String correctText){
            String link = urlGoogle.concat(replaceAllspaceOn20Percent(replaceIncorrectSymbals(correctText))).concat("&op=translate");
            return link;
    }
}
