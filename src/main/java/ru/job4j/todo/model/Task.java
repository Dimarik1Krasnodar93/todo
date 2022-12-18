package ru.job4j.todo.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate created = LocalDate.now();
    @NotNull
    private String description;
    @NotNull
    private boolean finished;
}
