package tech.vision8.jpa.demo.controllers;

import org.springframework.web.bind.annotation.*;
import tech.vision8.jpa.demo.errors.ResourceNotFoundException;
import tech.vision8.jpa.demo.model.Task;
import tech.vision8.jpa.demo.repos.TaskRepo;

import javax.validation.Valid;
import java.util.List;


/** The task controller. */
@RestController
@RequestMapping("/api")
public class TaskController {

    private TaskRepo taskRepo;

    public TaskController(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    @GetMapping("/tasks")
    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    @GetMapping("/tasks/{id}")
    public Task getTaskById(@PathVariable(value = "id") Long id) {
        return taskRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task", "id", id));
    }

    @PostMapping("/tasks")
    public Task createTask(@Valid @RequestBody Task task) {
        return taskRepo.save(task);
    }

}
