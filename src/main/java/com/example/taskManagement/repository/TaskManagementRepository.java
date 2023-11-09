package com.example.taskManagement.repository;

import com.example.taskManagement.model.Priority;
import com.example.taskManagement.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

@EnableJpaRepositories
public interface TaskManagementRepository extends JpaRepository<Task, String> {

    @Query(value = "SELECT * FROM #{#entityName} AS app WHERE app.TASKPRIORITY in :#{#priorities.![name()]}", nativeQuery = true)
    List<Task> getCriticalAndHighPriorityTasks(@Param("priorities")List<Priority> priorities);

    List<Task> findByTaskStartTimeBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);

    List<Task> findByLabelLabelNameAndTaskStartTimeBetween(String labelName, LocalDateTime startDateTime, LocalDateTime endDateTime);
}
