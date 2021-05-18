package by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.dao.service;

import by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.Bean.User;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.Bean.Word;
import by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.dao.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserRepositoriesService {

    @Autowired
    private UserRepository userRepository;


    public synchronized User findByChatId(Long chatId) {
        List<User> userList = userRepository.findAll();
        return userList.stream().filter(a -> a.getChatId().equals(chatId)).findAny().orElse(new User());

    }

    public synchronized List<User> findAll() {
        return userRepository.findAll();
    }

    public synchronized void saveUser(User user) {
        userRepository.save(user);
    }


    public synchronized boolean isWordExist(String word, Long chatid){
        User user = findByChatId(chatid);
        return user.getWordList().stream().anyMatch(a -> a.getName().equals(word));
    }

    public synchronized boolean isWordDelete(String word, Long chatid){
        if (isWordExist(word, chatid)){
            User user = findByChatId(chatid);
            return user.getWordList().stream().anyMatch(Word::isDelete);
        } else {
            return false;
        }
    }

    public synchronized void deleteWord(String name, long chatid){
        User user = findByChatId(chatid);
        List<Word> words = user.getWordList();
        for (Word word: words){
            if ( !word.isDelete() && word.getName().equals(name)){
                word.setDelete(true);
            }
        }
        userRepository.save(user);
    }

    public synchronized void restoreWord(String name, long chatid){
        User user = findByChatId(chatid);
        List<Word> words = user.getWordList();
        for (Word word: words){
            if (word.isDelete() && word.getName().equals(name)){
                word.setDelete(false);
            }
        }
        userRepository.save(user);
    }

    public synchronized List<Word> getListWords(long chatid){
        User user = findByChatId(chatid);
        List<Word> words = user.getWordList().stream().filter(a-> !a.isDelete()).collect(Collectors.toList());
        return words;
    }

    public synchronized List<Word> getListDeleteWords(long chatid){
        User user = findByChatId(chatid);
        List<Word> words = user.getWordList().stream().filter(a-> a.isDelete()).collect(Collectors.toList());
        return words;
    }
}
