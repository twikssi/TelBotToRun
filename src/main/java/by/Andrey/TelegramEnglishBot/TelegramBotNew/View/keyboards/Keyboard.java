package by.Andrey.TelegramEnglishBot.TelegramBotNew.View.keyboards;

import by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.Bean.WordVocabulary;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.View.MenuConstants.Menu;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Component
public class Keyboard {

    public ReplyKeyboardMarkup createMainMenu(){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        // Создаем список строк клавиатуры
        List<KeyboardRow> keyboard = new ArrayList<>();
        // Первая строчка клавиатуры
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        // Вторая строчка клавиатуры
        KeyboardRow keyboardSecondRow = new KeyboardRow();

        KeyboardRow keyboardThirdRow = new KeyboardRow();

        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        keyboard.clear();
        keyboardFirstRow.clear();
        // Добавляем кнопки в первую строчку клавиатуры
        keyboardFirstRow.add(Menu.mainMenuRandom);

        // Добавляем кнопки во вторую строчку клавиатуры
       // keyboardSecondRow.add(mainMenuSearch);
        keyboardSecondRow.add(Menu.mainMenuStatistic);

        keyboardThirdRow.add(Menu.mainMenuAddWord);

        // Добавляем все строчки клавиатуры в список
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        keyboard.add(keyboardThirdRow);
        // и устанваливаем этот список нашей клавиатуре
        replyKeyboardMarkup.setKeyboard(keyboard);
        return replyKeyboardMarkup;
    }


    public ReplyKeyboardMarkup createMenuSetupWords (){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        // Создаем список строк клавиатуры
        List<KeyboardRow> keyboard = new ArrayList<>();
        // Первая строчка клавиатуры
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        // Вторая строчка клавиатуры
        KeyboardRow keyboardSecondRow = new KeyboardRow();

        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        keyboard.clear();
        keyboardFirstRow.clear();
        // Добавляем кнопки в первую строчку клавиатуры
        keyboardFirstRow.add(Menu.menuAddWordInputWord);
        keyboardFirstRow.add(Menu.menuAddWordDelete);
        // Добавляем кнопки во вторую строчку клавиатуры
        keyboardSecondRow.add(Menu.menuCommandBack);
        keyboardSecondRow.add(Menu.MENU_ADDWORD_RUNSCHEDULE);

        // Добавляем все строчки клавиатуры в список
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        // и устанваливаем этот список нашей клавиатуре
        replyKeyboardMarkup.setKeyboard(keyboard);
        return replyKeyboardMarkup;
    }

    public ReplyKeyboardMarkup createMenuBack(){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        // Создаем список строк клавиатуры
        List<KeyboardRow> keyboard = new ArrayList<>();
        // Первая строчка клавиатуры
        KeyboardRow keyboardFirstRow = new KeyboardRow();

        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        keyboard.clear();
        keyboardFirstRow.clear();
        // Добавляем кнопки в первую строчку клавиатуры
        keyboardFirstRow.add(Menu.menuCommandBack);

        // Добавляем все строчки клавиатуры в список
        keyboard.add(keyboardFirstRow);

        // и устанваливаем этот список нашей клавиатуре
        replyKeyboardMarkup.setKeyboard(keyboard);
        return replyKeyboardMarkup;
    }

    public InlineKeyboardMarkup InlineKeyBoardMessageLink(String link1) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();


        inlineKeyboardButton2.setText("listen to examples");
        inlineKeyboardButton2.setUrl(link1);
        inlineKeyboardButton2.setCallbackData("linkcallbackquerynowords");


        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();

        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow2.add(inlineKeyboardButton2);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow2);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup InlineKeyBoardMessageLearn(String name) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();

        inlineKeyboardButton1.setText("learned");
        inlineKeyboardButton1.setCallbackData(name);


        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);


        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }

}
