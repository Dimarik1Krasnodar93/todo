package ru.job4j.todo.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.User;

import java.util.Map;
import java.util.Optional;

@Repository
public class HibernateUserRepository implements UserRepository {
    private final CrudRepository crudRepository;

    public HibernateUserRepository(CrudRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    @Override
    public Optional<User> add(User user) {
        return crudRepository.crud(user);
    }

    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) {
        String hql = "from User where login = :login and password = :password";
        Map<String, Object> args = Map.of("login", login, "password", password);
        return crudRepository.optional(hql, User.class, args);
    }
}