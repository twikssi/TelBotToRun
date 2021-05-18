package by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.Bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class User extends BaseEntity {

    private Long chatId;
    private String botStep;
    //fetch = FetchType.EAGER, mappedBy = "topic", cascade = CascadeType.ALL
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Word> wordList;
}
