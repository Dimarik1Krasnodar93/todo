package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.StaticSpring;
import ru.job4j.todo.model.Task;

@Repository
@AllArgsConstructor
public class TaskStore {
        public void addTask(Task task) {
            SessionFactory sf = StaticSpring.sf();
            Session session = sf.openSession();
            try {
                session.beginTransaction();
                session.save(task);
                session.getTransaction().commit();
            } catch (Exception ex) {
                ex.printStackTrace();
                session.getTransaction().rollback();
            }
            session.close();
        }
}
