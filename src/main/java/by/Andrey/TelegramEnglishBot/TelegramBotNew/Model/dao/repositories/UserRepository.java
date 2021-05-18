package by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.dao.repositories;

import by.Andrey.TelegramEnglishBot.TelegramBotNew.Model.Bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //Optional<User> findByChatId(Long chatId);
}
