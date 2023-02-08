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
    private final SessionFactory sessionFactory;
    private final CrudRepository crudRepository;
    private String findLoginPassword = "from User where name = :fld1 and password = :fld2";

    public HibernateUserRepository(SessionFactory sessionFactory, CrudRepository crudRepository) {
        this.sessionFactory = sessionFactory;
        this.crudRepository = crudRepository;
    }

    @Override
    public Optional<User> add(User user) {
        crudRepository.run(session -> session.save(user));
        return Optional.of(user);
    }

    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) {
        Map<String, Object> map = new HashMap<>();
        map.put("fld1", login);
        map.put("fld2", password);
        List<User> list = crudRepository.query(findLoginPassword, User.class, map);
        return list.size() > 0 ? Optional.of(list.get(0)) : Optional.empty();
    }
}
