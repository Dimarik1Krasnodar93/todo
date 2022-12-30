package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;;

import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class HibernateTaskRepository implements TaskRepository {

    private final SessionFactory sessionFactory;

    public static final String FIND_ALL_TASKS = "SELECT t FROM Task AS t";
    public static final String FIND_BY_DONE = "SELECT t FROM Task AS t WHERE t.done = :fDone";
    public static final String FIND_TASK_BY_ID = "SELECT t FROM Task AS t WHERE t.id = :fId";
    public static final String DONE_TASK_BY_ID = "UPDATE Task AS t SET done = true WHERE t.id = :fId";

    @Override
    public Task save(Task task) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(task);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
        return task;
    }

    @Override
    public void update(Task task) {
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.update(task);
            transaction.commit();
        } catch (Exception exception) {
            session.getTransaction().rollback();
            exception.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteById(int id) {
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            Task task = new Task();
            task.setId(id);
            session.delete(task);
            transaction.commit();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Task> findAll() {
        List<Task> result;
        Session session = sessionFactory.openSession();
        Query<Task> query = session.createQuery(FIND_ALL_TASKS, Task.class);
        try {
            result = query.list();
        } catch (Exception ex) {
            result = new ArrayList<>();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public List<Task> findByDone(boolean done) {
        List<Task> result;
        Session session = sessionFactory.openSession();
        Query<Task> query = session.createQuery(FIND_BY_DONE);
        query.setParameter("fDone", done);
        try {
            result = query.list();
        } catch (Exception ex) {
            result = new ArrayList<>();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public Task findById(int id) {
        Task result = null;
        Session session = sessionFactory.openSession();
        Query<Task> query = session.createQuery(FIND_TASK_BY_ID);
        query.setParameter("fId", id);
        try {
            result = query.uniqueResult();
        } finally {
            session.close();
        }
        return result;
    }

    public boolean doneTask(Task task) {
        boolean result = false;
        Session session = sessionFactory.openSession();
        Query<Task> query = session.createQuery(DONE_TASK_BY_ID);
        query.setParameter("fId", task.getId());
        try {
            session.beginTransaction();
            result = query.executeUpdate() > 0;
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
        }
        return result;
    }



}
