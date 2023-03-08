package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Priority;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.repository.TaskRepository;

import java.util.List;


@Service
@AllArgsConstructor
public class TaskService {
    private final SessionFactory sf;
    private final TaskRepository taskRepository;

    public static final String DONE_TASK_BY_ID = "UPDATE Task AS t SET done = true WHERE t.id = :fId";

    public List<Task> getAllTasks() {

        return taskRepository.findAll();
    }

    public List<Task> getDoneTasks() {

        return taskRepository.findByDone(true);
    }

    public List<Task> getNewTasks() {

        return taskRepository.findByDone(false);
    }

    public Task findById(int id) {
        return taskRepository.findById(id);
    }

    public void deleteById(int id) {
        taskRepository.deleteById(id);
    }

    public void update(Task task) {
        taskRepository.update(task);
    }

    public void addTask(Task task) {
        if (task.getPriority() == null) {
            task.setPriority(new Priority(1, "", 1));
        }
        taskRepository.save(task);
    }

    public boolean doneTask(Task task) {
        boolean result = false;
        Session session = sf.openSession();
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