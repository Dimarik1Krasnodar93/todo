package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

@Repository
@AllArgsConstructor
public class CrudRepository {
    private final SessionFactory sessionFactory;

    public void run(Consumer<Session> command) {
        tx(session -> {
            command.accept(session);
            return null;
        });
    }

    public <T> T tx(Function<Session, T> command) {
        var session = sessionFactory.openSession();
        try (session) {
            Transaction tx = session.beginTransaction();
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (Exception e) {
            Transaction tx = session.getTransaction();
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    public <T> Optional<T> optional(String query, Class<T> cl, Map<String, Object> args) {
        Function<Session, Optional<T>> command = session -> {
            var sq = session
                    .createQuery(query, cl);
            for (Map.Entry<String, Object> arg : args.entrySet()) {
                sq.setParameter(arg.getKey(), arg.getValue());
            }
            return sq.uniqueResultOptional();
        };
        return tx(command);
    }

    public <T> List<T> query(String query, Class<T> cl, Map<String, Object> args) {
        Function<Session, List<T>> command = session -> {
            var sq = session
                    .createQuery(query, cl);
            for (Map.Entry<String, Object> arg : args.entrySet()) {
                sq.setParameter(arg.getKey(), arg.getValue());
            }
            return sq.list();
        };
        return tx(command);
    }

    public <T> Optional<T> crud(T param) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(param);
            session.getTransaction().commit();
            return Optional.of(param);
        } catch (Exception ex) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return Optional.empty();
    }
}
