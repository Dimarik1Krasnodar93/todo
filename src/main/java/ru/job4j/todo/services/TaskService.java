package ru.job4j.todo.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.job4j.todo.StaticSpring;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.query.TaskQuery;
import ru.job4j.todo.store.TaskStore;

import java.util.List;

public class TaskService {
    private final SessionFactory sf = StaticSpring.sf();

    public List<Task> getAllTasks() {
        return getTasks(TaskQuery.FIND_ALL_TASKS);
    }

    public List<Task> getFinishedTasks() {
        return getTasks(TaskQuery.FIND_FINISHED_TASKS);
    }

    public List<Task> getNewTasks() {
        return getTasks(TaskQuery.FIND_MEW_TASKS);
    }

    private List<Task> getTasks(String strQuery) {
        List<Task> result;
        Session session = sf.openSession();
        Query query = session.createQuery(strQuery);
        result = query.list();
        session.close();
        return result;
    }
}
