package by.Andrey.TelegramEnglishBot.TelegramBotNew.Controller;

import by.Andrey.TelegramEnglishBot.BorisevichBot;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.Bean.User;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.constants.BotState;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.dao.service.UserRepositoriesService;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.process.ProcessRandomWord;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.process.schedule.Schedule;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.View.DrawMessage;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.View.MenuConstants.Menu;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.View.SetupMessage;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.View.keyboards.Keyboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;

import java.util.ArrayList;
import java.util.List;

@Component
public class MenuSetupController {
    @Autowired
    private Keyboard keyboard;
    @Autowired
    private BorisevichBot borisevichBot;
    @Autowired
    private SetupMessage setupMessage;
    @Autowired
    private UserRepositoriesService userRepositoriesService;
    @Autowired
    private DrawMessage drawMessage;
    @Autowired
    private ProcessRandomWord processRandomWord;
    List<Thread> threadList = new ArrayList<>();

    List<Schedule> schedules = new ArrayList<>();

    public void runSetupMenu(Message message, User user) {
        String text = message.getText();
        long chatId = message.getChatId();

        if (text.equals(Menu.menuAddWordInputWord)) {
            user.setBotStep(BotState.INPUT_WORD_TO_ADD);
            userRepositoriesService.saveUser(user);

            String wordsNoDelete = "Words for learning: \n" + drawMessage.drawListWords(userRepositoriesService.getListWords(chatId));
            String wordsDeleted = "Deleted Words: \n" + drawMessage.drawListWords(userRepositoriesService.getListDeleteWords(chatId));


            borisevichBot.sendMessage(
                    setupMessage.setupMessageWithKeyboard(
                            message.getChatId(),
                            keyboard.createMenuBack(),
                            Menu.ENTER_WORD + "\n" + wordsNoDelete + wordsDeleted
                    )
            );

        } else if (text.equals(Menu.menuAddWordDelete)) {
            user.setBotStep(BotState.INPUT_WORD_TO_DELETE);
            userRepositoriesService.saveUser(user);

            String wordsNoDelete = "Words for learning: \n" + drawMessage.drawListWords(userRepositoriesService.getListWords(chatId));
            String wordsDeleted = "Deleted Words: \n" + drawMessage.drawListWords(userRepositoriesService.getListDeleteWords(chatId));

            borisevichBot.sendMessage(
                    setupMessage.setupMessageWithKeyboard(
                            message.getChatId(),
                            keyboard.createMenuBack(),
                            Menu.ENTER_WORD + "\n" + wordsNoDelete + wordsDeleted
                    )
            );
        } else if (text.equals(Menu.menuCommandBack)) {
            user.setBotStep(BotState.MAIN_MENU);
            userRepositoriesService.saveUser(user);

            borisevichBot.sendMessage(
                    setupMessage.setupMessageWithKeyboard(
                            message.getChatId(),
                            keyboard.createMainMenu(),
                            Menu.CHOOSE_COMMAND
                    )
            );
        } else if (text.equals(Menu.MENU_ADDWORD_RUNSCHEDULE)) {
            boolean isExistSchedule = schedules.stream().anyMatch(a -> a.getChatId() == (chatId));

            if (isExistSchedule) {
                for (Schedule schedule : schedules) {
                    //System.out.println(schedule.getUser().getChatId().toString());
                    if (schedule.getChatId() == chatId) {
                        schedule.setSchedulOn(false);
                        schedules.remove(schedule);
                       // System.out.println("deleted");
                    }
                }
            } else {
                Schedule schedule = new Schedule();
                schedule.setChatid(chatId);
                schedule.setUser(user);
                schedule.setProcessRandomWord(processRandomWord);
                schedule.setSchedulOn(true);
                Thread thread = new Thread(schedule);
                //thread.setName(Long.toString(chatId));
                thread.start();
                schedules.add(schedule);

                //System.out.println(thread.getName());
            }
        }
    }
}
