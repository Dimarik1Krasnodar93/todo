package ru.job4j.todo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import ru.job4j.util.PropertiesAdditional;


import javax.persistence.*;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Table(name = "tasks")
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private LocalDateTime created = LocalDateTime.now();
    @NonNull
    private String description;

    private boolean done;



    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "priority_id")
    private Priority priority;

    @ManyToMany
    @JoinTable(
            name = "categories_tasks",
            joinColumns = {@JoinColumn(name = "task_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")}
    )
    private List<Category> categories = new ArrayList<>();


    public Task(int id, LocalDateTime created, @NonNull String description, boolean done) {
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

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public LocalDateTime getCreatedTimeZone() {
        String stringTZ = ZoneId.systemDefault().toString();
        ZonedDateTime nowInUTC = ZonedDateTime.of(created,
                ZoneId.of(PropertiesAdditional.properties.getProperty("timezone")));
        return nowInUTC.withZoneSameInstant(ZoneId.of(stringTZ)).toLocalDateTime();
    }
}
