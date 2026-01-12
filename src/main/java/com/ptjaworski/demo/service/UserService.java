package com.ptjaworski.demo.service;

import com.ptjaworski.demo.dto.UserRequestDto;
import com.ptjaworski.demo.dto.UserResponseDto;
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

    private static Map<Long, UserRequestDto> usersMap; // jpa/hibernate превращает эту хуйню с базы в меп, после - взаимодействие как с обычным мепом

    public UserService(UserRepository userRepository) { // конструктор, инициализирую статик каунтер и репик. принимаю jpa от экземпляра
        this.repository = userRepository;
        this.usersMap = new HashMap<Long, UserRequestDto>();
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
                        ue.getEmail(),
                        null
                )
        ).toList();
    }

    public static UserResponseDto registerUser(UserRequestDto user) {
        if(user.id() != null) {
            throw new IllegalArgumentException("New user cannot have an ID field");
        }
        if(usersMap.containsKey(user.username())) {
            throw new IllegalArgumentException("Username already exists");
        }
        if(user.password().length() < 8) {
            throw new IllegalArgumentException("Password should be at least 8 characters");
        }
        var newUserEntity = new UserRequestDto(
                idCounter.incrementAndGet(),
                user.firstName(),
                user.lastName(),
                user.username(),
                user.email(),
                "pwd в виде хеша"
        );
        usersMap.put(newUserEntity.id(), newUserEntity);
        var userFromDB = usersMap.get(newUserEntity.id());
        var newUserEntityResponse = new UserResponseDto(
                userFromDB.id(),
                userFromDB.firstName(),
                userFromDB.lastName(),
                userFromDB.username(),
                userFromDB.email()
        );

        return newUserEntityResponse;
    }



}
