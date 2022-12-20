package ru.job4j.todo.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;
import ru.job4j.todo.StaticSpring;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.query.TaskQuery;
import ru.job4j.todo.store.TaskStore;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    private final SessionFactory sf = StaticSpring.sf();

    private final TaskStore taskStore;

    public TaskService(TaskStore taskStore) {
        this.taskStore = taskStore;
    }

    public List<Task> getAllTasks() {
        return getTasks(TaskQuery.FIND_ALL_TASKS);
    }

    public List<Task> getDoneTasks() {
        return getTasks(TaskQuery.FIND_DONE_TASKS);
    }

    public List<Task> getNewTasks() {
        return getTasks(TaskQuery.FIND_MEW_TASKS);
    }

    /*try-finally сделал чтобы не возникала ошибка при отсутствии элементов в query
    без catch (Exception ex) {
            result = new ArrayList<>();
        } возникала ошибка что отношение не существует, даже когда в самом начале я писал
        result = new ArrayList<>();
    * */
    private List<Task> getTasks(String strQuery) {
        List<Task> result;
        Session session = sf.openSession();
        Query<Task> query = session.createQuery(strQuery, Task.class);
        try {
            result = query.list();
        } catch (Exception ex) {
            result = new ArrayList<>();
        } finally {
            session.close();
        }
        return result;
    }

    public void addTask(Task task) {
        taskStore.addTask(task);
    }


}
