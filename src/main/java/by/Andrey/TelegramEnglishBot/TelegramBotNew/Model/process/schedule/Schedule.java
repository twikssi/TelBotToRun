package by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.process.schedule;

import by.Andrey.TelegramEnglishBot.BorisevichBot;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.Bean.User;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.dao.service.UserRepositoriesService;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.process.ProcessRandomWord;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Component
@Data
public class Schedule implements Runnable {

    private ProcessRandomWord processRandomWord;
    private long chatId;
    private User user;
    private boolean schedulOn = false;


    public void setChatid(long chatid) {
        this.chatId = chatid;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setProcessRandomWord(ProcessRandomWord processRandomWord) {
        this.processRandomWord = processRandomWord;
    }

    public void setSchedulOn(boolean schedulOn) {
        this.schedulOn = schedulOn;
    }

    public boolean isSchedulOn() {
        return schedulOn;
    }

    public void runSchedule() {
        while (schedulOn) {

            Calendar calendar = new GregorianCalendar();
            calendar.getTime();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);


            if (hour >= 10 && hour <= 22) {
                processRandomWord.run(user);
                try {
                    Thread.sleep(3600000); //60000 - one minute
                  //  Thread.sleep(20000); //60000 - one minute
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Thread.sleep(3600000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void run() {
        runSchedule();
    }
}