package ru.job4j.todo.query;

public class TaskQuery {
    public static final String FIND_ALL_TASKS = "SELECT t FROM Task AS t";
    public static final String FIND_MEW_TASKS = "SELECT t FROM Task AS t WHERE t.done = false";
    public static final String FIND_DONE_TASKS = "SELECT t FROM Task AS t WHERE t.done = true";
    public static final String FIND_TASK_BY_ID = "SELECT t FROM Task AS t WHERE t.id = :fId";
    public static final String DONE_TASK_BY_ID = "UPDATE Task AS t SET done = true WHERE t.id = :fId";
}
