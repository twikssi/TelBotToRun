package by.Andrey.TelegramEnglishBot.TelegramBotNew.View;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;

@Component
public class SetupMessage {
    public SendMessage setupMessage(Long chatid, String text){
        return  new SendMessage().setChatId(chatid).setText(text);
    }

    public SendMessage setupMessageWithKeyboard(Long chatid, ReplyKeyboard replyKeyboard, String text){
        return  new SendMessage().setChatId(chatid).setText(text).setReplyMarkup(replyKeyboard);
    }
}
