package com.example.taskManagement.controller;

import com.example.taskManagement.ApiUrls;
import com.example.taskManagement.model.Task;
import com.example.taskManagement.service.TaskManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(ApiUrls.ROOT_URL_TASKS)
public class TaskManagementRestController {
  private final Logger logger = LoggerFactory.getLogger(TaskManagementRestController.class);
  private final TaskManagementService taskManagementService;

  @Autowired
  public TaskManagementRestController(TaskManagementService taskManagementService) {
    this.taskManagementService = taskManagementService;
  }

  @GetMapping("/nextNDays/{nextNDays}")
  public ResponseEntity<?> findAll(@PathVariable("nextNDays") Integer nextNDays) {
    logger.trace("findAll: nextNDays = {}", nextNDays);

    List<Task> taskList = taskManagementService.findAllTasksForNextNDays(nextNDays);
    return ResponseEntity.ok(taskList);
  }


  @GetMapping("/allCriticalAndHighPriorityTasks")
  public ResponseEntity<?> findAll() {
    logger.trace("findAll()");

    List<Task> taskList = taskManagementService.findAllCriticalAndHighPriorityTasks();
    return ResponseEntity.ok(taskList);
  }

  @GetMapping("/allTasksByLabelNameForToday/{labelName}")
  public ResponseEntity<?> findAll(@PathVariable("labelName") String labelName) {
    logger.trace("findAll: labelName = {}", labelName);

    List<Task> taskList = taskManagementService.findAllTasksByLabelNameForToday(labelName);
    return ResponseEntity.ok(taskList);
  }

  @GetMapping(ApiUrls.URL_TASKS_TASK)
  public ResponseEntity<?> findOne(@PathVariable("taskId") String taskId) {
    logger.trace("findOne: taskId = {}", taskId);

    Task task = taskManagementService.findOne(taskId);
    return ResponseEntity.ok(task);
  }

  @PostMapping
  public ResponseEntity<?> createTask(@RequestBody Task task) {
    logger.trace("save");

    task = taskManagementService.save(task);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(task.getId()).toUri();
    return ResponseEntity.created(location).body(task);
  }

  @PutMapping(ApiUrls.URL_TASKS_TASK)
  public ResponseEntity<?> updateTask(@PathVariable("taskId") String taskId, @RequestBody Task task) {
    logger.trace("update: taskId = {}", taskId);

    task.setId(taskId);
    task = taskManagementService.update(taskId, task);
    return ResponseEntity.ok(task);
  }


  @DeleteMapping(ApiUrls.URL_TASKS_TASK)
  public ResponseEntity<?> deleteTask(@PathVariable("taskId") String taskId) {
    logger.trace("delete: taskId = {}", taskId);

    taskManagementService.delete(taskId);
    return ResponseEntity.noContent().build();
  }
}
