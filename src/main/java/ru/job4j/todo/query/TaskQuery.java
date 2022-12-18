package ru.job4j.todo.query;

public class TaskQuery {
    public static final String FIND_ALL_TASKS = "from Task";
    public static final String FIND_MEW_TASKS = "from Task where finished = false";
    public static final String FIND_FINISHED_TASKS = "from Task where finished = false";
}
