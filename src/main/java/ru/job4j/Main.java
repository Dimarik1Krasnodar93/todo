package ru.job4j;

import org.hibernate.HibernateException;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.job4j.todo.StaticSpring;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        try {
            StaticSpring.sf();
        } catch (HibernateException ex) {
            System.out.println("Problem creating factory");
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
