package com.example.taskManagement.service;

import com.example.taskManagement.model.Priority;
import com.example.taskManagement.model.Task;
import com.example.taskManagement.repository.TaskManagementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class TaskManagementService {
  private final Logger logger = LoggerFactory.getLogger(TaskManagementService.class);
  private final TaskManagementRepository taskManagementRepository;

  @Autowired
  public TaskManagementService(TaskManagementRepository taskManagementRepository) {
    this.taskManagementRepository = taskManagementRepository;
  }

  public List<Task> findAllCriticalAndHighPriorityTasks() {
    List<Priority> priorityList = new ArrayList<>();
    priorityList.add(Priority.CRITICAL);
    priorityList.add(Priority.HIGH);
    return taskManagementRepository.getCriticalAndHighPriorityTasks(priorityList);
  }

  public List<Task> findAllTasksForNextNDays(Integer nextNDays) {
    LocalDateTime currentDateTime = LocalDateTime.now();
    LocalDateTime afterNDaysDateTime = currentDateTime.plusDays(nextNDays);
    LocalDateTime futureDateTime = afterNDaysDateTime.with(LocalTime.MAX);
    return taskManagementRepository.findByTaskStartTimeBetween(currentDateTime, futureDateTime);
  }

  public List<Task> findAllTasksByLabelNameForToday(String labelName) {
    LocalDateTime currentDateTime = LocalDateTime.now();
    LocalDateTime startOfDay = currentDateTime.with(LocalTime.MIN);
    LocalDateTime endOfDay = currentDateTime.with(LocalTime.MAX);
    return taskManagementRepository.findByLabelLabelNameAndTaskStartTimeBetween(labelName, startOfDay, endOfDay);
  }

  public Task findOne(String taskId) {
    return taskManagementRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task with this taskId not present"));
  }

  @Transactional
  public Task save(Task task) {
    logger.debug("Task Entity before persisting: " + task);

    String uuid = UUID.randomUUID().toString();
    task.setId(uuid);
    return taskManagementRepository.save(task);
  }

  @Transactional
  public Task update(String taskId, Task task) {
    Task mTask = taskManagementRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task with this taskId not present"));

    mTask.setTaskName(task.getTaskName());
    mTask.setLabel(task.getLabel());
    mTask.setTaskPriority(task.getTaskPriority());
    mTask.setTaskStartTime(task.getTaskStartTime());

    return mTask;
  }

  @Transactional
  public void delete(String taskId) {
    Task task = taskManagementRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task with this taskId not present"));
    taskManagementRepository.delete(task);
  }
}
