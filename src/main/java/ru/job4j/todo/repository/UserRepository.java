package ru.job4j.todo.repository;

import ru.job4j.todo.model.User;

import java.util.Optional;

public interface UserRepository {
     String FIND_LOGIN_PASSWORD = "from User where name = :fld and password = :fld";
     String FIND_LOGIN = "SELECT * FROM USERS WHERE login = :fld1";

    Optional<User> add(User user);
    Optional<User> findUserByLoginAndPassword(String login, String password);
}
