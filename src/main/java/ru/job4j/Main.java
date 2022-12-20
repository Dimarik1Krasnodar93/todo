package ru.job4j;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.job4j.todo.StaticSpring;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        StaticSpring.sf();
        SpringApplication.run(Main.class, args);
    }
}
