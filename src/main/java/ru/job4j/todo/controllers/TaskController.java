package ru.job4j.todo.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.model.User;
import ru.job4j.todo.service.TaskService;
import ru.job4j.util.UserAdditional;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@AllArgsConstructor
@Controller
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/tasks")
    public String tasks(Model model, @ModelAttribute User user, HttpSession httpSession) {
        user = UserAdditional.getFromHttpSession(httpSession);
        model.addAttribute("user", user);

        model.addAttribute("tasks", taskService.getAllTasks());

        return "tasks";
    }

    @GetMapping("/newTasks")
    public String newTasks(Model model, HttpSession httpSession) {
        User user = UserAdditional.getFromHttpSession(httpSession);
        model.addAttribute("user", user);
        model.addAttribute("tasks", taskService.getNewTasks());
        return "tasks";
    }

    @GetMapping("/doneTasks")
    public String finishedTasks(Model model, HttpSession httpSession) {
        User user = UserAdditional.getFromHttpSession(httpSession);
        model.addAttribute("user", user);
        model.addAttribute("tasks", taskService.getDoneTasks());
        return "redirect:/tasks";
    }

    @GetMapping("/formAddTask")
    public String formAddTask(Model model, HttpSession httpSession) {
        User user = UserAdditional.getFromHttpSession(httpSession);
        model.addAttribute("user", user);
        model.addAttribute("task", new Task(0, LocalDate.now(), "", false));
        return "addTask";
    }

    @PostMapping("/createTask")
        public String createTask(Model model, @ModelAttribute Task task, HttpSession httpSession) {
            User user = UserAdditional.getFromHttpSession(httpSession);
            model.addAttribute("user", user);
            taskService.addTask(task);
            return "redirect:/tasks";
    }

    @GetMapping("/task/{id}")
    public String task(Model model, @PathVariable("id") int id, HttpSession httpSession) {
        User user = UserAdditional.getFromHttpSession(httpSession);
        model.addAttribute("user", user);
        Task task = taskService.findById(id);
        model.addAttribute("task", task);
        return "one";
    }


    @GetMapping("/formTask")
    public String formTask(Model model,  HttpSession httpSession) {
        User user = UserAdditional.getFromHttpSession(httpSession);
        model.addAttribute("user", user);
        return "tasks";
    }

    @GetMapping("/formUpdateTask/{id}")
    public String formUpdateTaskNew(Model model, @PathVariable("id") int id, HttpSession httpSession) {
        User user = UserAdditional.getFromHttpSession(httpSession);
        model.addAttribute("user", user);
        Task task = taskService.findById(id);
        model.addAttribute("task", task);
        return "updateTask";
    }

    @PostMapping("/updateTask")
    public String formUpdateTask(
            @ModelAttribute Task task, Model model, HttpSession httpSession) {
        User user = UserAdditional.getFromHttpSession(httpSession);
        model.addAttribute("user", user);
        taskService.update(task);
        return "tasks";
    }

    @GetMapping("/updateTask")
    public String formUpdateTask2(
            @ModelAttribute Task task, Model model, HttpSession httpSession) {
        User user = UserAdditional.getFromHttpSession(httpSession);
        model.addAttribute("user", user);
        return "tasks";
    }

    @PostMapping("/doneTask")
    public String doneTask(@ModelAttribute Task task, Model model, HttpSession httpSession) {
        User user = UserAdditional.getFromHttpSession(httpSession);
        model.addAttribute("user", user);
        taskService.doneTask(task);
        return "redirect:/tasks";
    }

    @GetMapping("/deleteTask/{id}")
    public String deleteTask(@PathVariable("id") int id, Model model, HttpSession httpSession) {
        User user = UserAdditional.getFromHttpSession(httpSession);
        model.addAttribute("user", user);
        taskService.deleteById(id);
        return "tasks";
    }

    @GetMapping("/index")
    public String index(Model model, HttpSession httpSession) {
        User user = UserAdditional.getFromHttpSession(httpSession);
        model.addAttribute("user", user);
        return "index";
    }
}
