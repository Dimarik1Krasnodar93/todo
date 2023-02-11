package ru.job4j.todo.repository;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.User;

import java.util.HashMap;
import java.util.List;
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
        crudRepository.run(session -> session.save(user));
        return Optional.of(user);
    }

    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) {
        String hql = "from User where name = :login and password = :password";
        Map<String, Object> map = Map.of("login", login,
        "password", password);
        List<User> list = crudRepository.query(hql, User.class, map);
        return list.size() > 0 ? Optional.of(list.get(0)) : Optional.empty();
    }
}
