package com.ptjaworski.demo.service;

import com.ptjaworski.demo.model.User;
import com.ptjaworski.demo.model.UserEntity;
import com.ptjaworski.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {
    // прайвет тк инкапсуляция
    private final UserRepository repository; // репозиторий, расширялка Jpa
    private static AtomicLong idCounter;  // каунтер айдишек, статик для глобального значения

    public UserService(UserRepository userRepository) { // конструктор, инициализирую статик каунтер и репик. принимаю jpa от экземпляра
        this.repository = userRepository;
        idCounter = new AtomicLong();
    }

    // метод сервиса, возвращает всех пользователей с бд.
    public List<User> getAllUsers() {
        List<UserEntity> allEntities = repository.findAll();

        return allEntities.stream().map(
                ue -> new User(
                        ue.getId(),
                        ue.getFirstName(),
                        ue.getLastName(),
                        ue.getUsername(),
                        ue.getEmail()
                )
        ).toList();
    }

}
