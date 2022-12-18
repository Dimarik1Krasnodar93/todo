package ru.job4j.todo.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.services.TaskService;

import java.time.LocalDate;

@AllArgsConstructor
@Controller
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/tasks")
    public String tasks(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        return "tasks";
    }

    @GetMapping("/newTasks")
    public String newTasks(Model model) {
        model.addAttribute("tasks", taskService.getNewTasks());
        return "tasks";
    }

    @GetMapping("/finishedTasks")
    public String finishedTasks(Model model) {
        model.addAttribute("tasks", taskService.getFinishedTasks());
        return "tasks";
    }

    @GetMapping("/formAddTask")
    public String formAddTask(Model model) {
        model.addAttribute("task", new Task(0, LocalDate.now(), "", false));
        return "addTask";
    }
}
