package ru.job4j.todo.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "categories")
@Entity
@Data
public class Category {
    @Id
    @Column
    private int id;
    @Column
    private String name;
}
