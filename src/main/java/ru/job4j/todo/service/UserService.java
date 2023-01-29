package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.User;
import ru.job4j.todo.repository.UserRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public final static String FIND_LOGIN_PASSWORD = "from User where name = :fld and password = :fld";
    public final static String FIND_LOGIN = "SELECT * FROM USERS WHERE login = :fld1";

    public Optional<User> add(User user) {
        return userRepository.add(user);
    }

    public Optional<User> findUserByLoginAndPassword(String login, String password) {
        return userRepository.findUserByLoginAndPassword(login, password);
    }

}
