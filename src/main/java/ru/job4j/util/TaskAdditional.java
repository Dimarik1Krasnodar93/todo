package ru.job4j.util;

import ru.job4j.todo.model.Task;

import java.time.*;

public class TaskAdditional {
    public static void updateTaskWithTimeZone(Task task, String timezone) {
        LocalDateTime created = task.getCreated();
        ZonedDateTime nowInUTC = ZonedDateTime.of(created,
                ZoneId.of(ZoneId.systemDefault().toString()));
        LocalDateTime ltd = nowInUTC.withZoneSameInstant(ZoneId.of(timezone)).toLocalDateTime();
        task.setCreated(ltd);
    }
}
