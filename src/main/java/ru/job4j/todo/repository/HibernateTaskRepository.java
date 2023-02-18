package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;;

import java.util.*;

@Repository
public class HibernateTaskRepository implements TaskRepository {

    private final SessionFactory sessionFactory;
    private final CrudRepository crudRepository;

    public static final String FIND_ALL_TASKS = "SELECT t FROM Task AS t JOIN FETCH t.priority";
    public static final String FIND_BY_DONE = "SELECT t FROM Task AS t WHERE t.done = :fDone JOIN FETCH t.priority";
    public static final String FIND_TASK_BY_ID = "SELECT t FROM Task AS t WHERE t.id = :fId";

    public HibernateTaskRepository(SessionFactory sessionFactory, CrudRepository crudRepository) {
        this.sessionFactory = sessionFactory;
        this.crudRepository = crudRepository;
    }

    @Override
    public Task save(Task task) {
        crudRepository.run(session -> session.save(task));
        return task;
    }

    @Override
    public void update(Task task) {
        crudRepository.run(session -> session.update(task));
    }

    @Override
    public void deleteById(int id) {
        Task task = new Task();
        task.setId(id);
        crudRepository.run(session -> session.delete(task));
    }

    @Override
    public List<Task> findAll() {
        return crudRepository.query(FIND_ALL_TASKS, Task.class, new HashMap<>());
    }

    @Override
    public List<Task> findByDone(boolean done) {
        Map<String, Object> map = new HashMap<>();
        map.put("fDone", done);
        return crudRepository.query(FIND_BY_DONE, Task.class, map);
    }

    @Override
    public Task findById(int id) {
        Map<String, Object> map = new HashMap<>();
        map.put("fId", id);
        return crudRepository.query(FIND_TASK_BY_ID, Task.class, map).get(0);
    }

    public boolean doneTask(Task task) {
        Map<String, Object> map = new HashMap<>();
        map.put("fId", task.getId());
        task.setDone(true);
        try {
            crudRepository.run(session -> session.persist(task));
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }



}
