package ru.job4j.todo.repository;

import ru.job4j.todo.model.Task;

import java.util.List;

public interface TaskRepository {
    Task save(Task task);

    void update(Task task);

    void deleteById(int id);

    List<Task> findAll();

    List<Task> findByDone(boolean done);

    Task findById(int id);
}
