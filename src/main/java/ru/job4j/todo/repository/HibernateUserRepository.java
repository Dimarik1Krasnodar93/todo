package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.User;
import ru.job4j.todo.service.UserService;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class HibernateUserRepository implements UserRepository {
    private final SessionFactory sessionFactory =
            new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();

    @Override
    public Optional<User> add(User user) {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) {
        Session session = sessionFactory.openSession();
        Optional<User> result = Optional.empty();
        try {
            Query<User> query = session.createQuery(UserService.FIND_LOGIN_PASSWORD, User.class);
            query.setParameter("fld", login);
            query.setParameter("fld", password);
            session.beginTransaction();
            result = query.setFirstResult(0).uniqueResultOptional();
            session.getTransaction().commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
        }
        return result;
    }
}
