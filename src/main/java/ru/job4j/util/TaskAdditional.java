package ru.job4j.util;

import ru.job4j.todo.model.Task;

import java.time.*;

public class TaskAdditional {
    public static void updateTaskWithTimeZone(Task task, String timezone) {
        LocalDate created = task.getCreated();
        ZonedDateTime nowInUTC = ZonedDateTime.of(LocalDateTime.of(created, LocalTime.now()),
                ZoneId.of(timezone));
        task.setCreated(nowInUTC.toLocalDate());
    }
}
