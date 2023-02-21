package ru.job4j.todo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column
    private int id;
    @Column
    private String name;
    @Column
    private String login;
    @Column
    private String password;
    @Column
    private String userZone;

    @OneToMany (mappedBy = "user")
    private List<Task> taskList;


    public User(int id, String name, String login, String password) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\'' +  '}';
    }



}
