package ru.job4j.todo.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.service.TaskService;

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

    @GetMapping("/doneTasks")
    public String finishedTasks(Model model) {
        model.addAttribute("tasks", taskService.getDoneTasks());
        return "redirect:/tasks";
    }

    @GetMapping("/formAddTask")
    public String formAddTask(Model model) {
        model.addAttribute("task", new Task(0, LocalDate.now(), "", false));
        return "addTask";
    }

    @PostMapping("/createTask")
    public String createTask(@ModelAttribute Task task) {
        taskService.addTask(task);
        return "redirect:/tasks";
    }

    @GetMapping("/task/{id}")
    public String task(Model model, @PathVariable("id") int id) {
        Task task = taskService.findById(id);
        model.addAttribute("task", task);
        return "one";
    }

    @GetMapping("/formTask")
    public String formTask(Model model) {

        return "tasks";
    }

    @GetMapping("/formUpdateTask/{id}")
    public String formUpdateTaskNew(Model model, @PathVariable("id") int id) {
        Task task = taskService.findById(id);
        model.addAttribute("task", task);
        return "formUpdateTask";
    }

    @PostMapping("/updateTask")
    public String formUpdateTask(
            @ModelAttribute Task task) {
        return "tasks";
    }

    @GetMapping("/updateTask")
    public String formUpdateTask2(
            @ModelAttribute Task task) {
        return "tasks";
    }

    @GetMapping("/updateTask2/{id}")
    public String updateTask2(Model model, @PathVariable("id") int id) {
        return "tasks";
    }

    @PostMapping("/formUpdateTaskN/{id}")
    public String updateTaskN(Model model, @PathVariable("id") int id) {
        Task task = taskService.findById(id);
        model.addAttribute("task", task);
        return "redirect:/formUpdateTaskNew";
    }


    @PostMapping("/doneTask")
    public String doneTask(@ModelAttribute Task task) {
        taskService.doneTask(task);
        return "redirect:/tasks";
    }

    @PostMapping("/deleteTask")
    public String deleteTask(Model model) {
        return "tasks";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }
}
