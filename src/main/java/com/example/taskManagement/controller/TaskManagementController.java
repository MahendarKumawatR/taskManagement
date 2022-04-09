package com.example.taskManagement.controller;

import com.example.taskManagement.model.Task;
import com.example.taskManagement.service.TaskManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class TaskManagementController {

    private TaskManagementService taskManagementService;

    @Autowired
    public TaskManagementController(TaskManagementService taskManagementService) {
        this.taskManagementService = taskManagementService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/Tasks")
    public ResponseEntity<Void> createTask(@RequestBody Task task){
        Task taskCreated = taskManagementService.createTask(task);
        URI uri = URI.create("/Tasks/" + taskCreated.getId());
        return ResponseEntity.created(uri).build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/Tasks/{taskId}")
    public ResponseEntity<Task> getTask(@PathVariable("taskId") String taskId){
        Task task = taskManagementService.getTask(taskId);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/Tasks/{taskId}")
    public ResponseEntity<Void> updateTask(@PathVariable("taskId") String taskId, @RequestBody Task task){
        Task task1 = taskManagementService.updateTask(taskId, task);
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/Tasks/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable("taskId") String taskId){
        taskManagementService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/Tasks/nextNDays/{nextNDays}")
    public List<Task> getTask1(@PathVariable("nextNDays") Integer nextNDays){
        return taskManagementService.getAllTasksForNextNDays(nextNDays);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/Tasks/allCriticalAndHighPriorityTasks")
    public List<Task> getAllCriticalAndHighPriorityTasks(){
        return taskManagementService.getAllCriticalAndHighPriorityTasks();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/Tasks/allTasksByLabelNameForToday/{labelName}")
    public List<Task> getAllTasksByLabelNameForToday(@PathVariable("labelName") String labelName){
        return taskManagementService.getAllTasksByLabelNameForToday(labelName);
    }
}
