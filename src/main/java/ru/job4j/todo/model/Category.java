package ru.job4j.todo.model;

import lombok.Data;

import javax.persistence.*;

@Table(name = "categories")
@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
}
