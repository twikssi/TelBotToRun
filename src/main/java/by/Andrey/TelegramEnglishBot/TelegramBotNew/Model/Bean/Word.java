package by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.Bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Word extends BaseEntity {

    private String name;
    private long numberOfRepetitions;
    private boolean isDelete = false;

}
