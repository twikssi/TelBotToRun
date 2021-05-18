package by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.Bean;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WordVocabulary extends BaseEntity{

    private String name;
    private String partsOfSpeech;
    private String transcription;
    private String meaning;
    private List<String> examples;

    @Override
    public String toString() {
        return "WordVocabulary{" +
                "name='" + name + '\'' +
                ", partsOfSpeech='" + partsOfSpeech + '\'' +
                ", transcription='" + transcription + '\'' +
                ", meaning='" + meaning + '\'' +
                ", examples=" + examples +
                '}';
    }
}
