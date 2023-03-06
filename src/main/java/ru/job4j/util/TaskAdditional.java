package ru.job4j.util;

import ru.job4j.todo.model.Task;

import java.time.*;
import java.util.List;

public class TaskAdditional {
    public static void updateTaskWithTimeZone(Task task, String timezone) {
        LocalDateTime created = task.getCreated();
        ZonedDateTime nowInUTC = ZonedDateTime.of(created,
                ZoneId.of(ZoneId.systemDefault().toString()));
        LocalDateTime ltd = nowInUTC.withZoneSameInstant(ZoneId.of(timezone)).toLocalDateTime();
        task.setCreated(ltd);
    }

    public static void updateTaskWithUserZone(Task task, String timezone) {
        LocalDateTime created = task.getCreated();
        ZonedDateTime nowInUTC = ZonedDateTime.of(created,
                ZoneId.of(PropertiesAdditional.properties.getProperty("timezone")));
        LocalDateTime ltd = nowInUTC.withZoneSameInstant(ZoneId.of(timezone)).toLocalDateTime();
        task.setCreated(ltd);
    }

    public static void updateListTasksWithUserZone(List<Task> list) {
        for (Task task : list) {
            TaskAdditional.updateTaskWithUserZone(task, ZoneId.systemDefault().toString());
        }
    }
}
