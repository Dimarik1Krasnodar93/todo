package ru.job4j.todo.query;

public class TaskQuery {
    public static final String FIND_ALL_TASKS = "SELECT t from Task as t";
    public static final String FIND_MEW_TASKS = "SELECT t from Task as t where t.done = false";
    public static final String FIND_DONE_TASKS = "SELECT t from Task as t where t.done = true";
}
