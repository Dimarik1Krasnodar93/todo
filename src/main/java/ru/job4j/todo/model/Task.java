package ru.job4j.todo.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tasks")
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate created = LocalDate.now();
    @NonNull
    private String description;

    private boolean done;



    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Task(int id, LocalDate created, @NonNull String description, boolean done) {
        this.id = id;
        this.created = created;
        this.description = description;
        this.done = done;
    }

    public boolean getDone() {
        return done;
    }

    public User getUser() {
        return user;
    }

}
