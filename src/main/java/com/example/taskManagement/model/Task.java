package com.example.taskManagement.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "TASK")
public class Task {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "TASKNAME")
    private String taskName;

    @Embedded
    private Label label;

    @Enumerated(EnumType.STRING)
    @Column(name = "TASKPRIORITY")
    private Priority taskPriority;

    @Column(name = "TASKSTARTTIME")
    private LocalDateTime taskStartTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public Priority getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(Priority taskPriority) {
        this.taskPriority = taskPriority;
    }

    public LocalDateTime getTaskStartTime() {
        return taskStartTime;
    }

    public void setTaskStartTime(LocalDateTime taskStartTime) {
        this.taskStartTime = taskStartTime;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Task task = (Task) object;
        return Objects.equals(id, task.id) && Objects.equals(taskName, task.taskName) && Objects.equals(label, task.label) && Objects.equals(taskStartTime, task.taskStartTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, taskName, label, taskStartTime);
    }

    @Override
    public String toString() {
        return "Task{" +
            "id='" + id + '\'' +
            ", taskName='" + taskName + '\'' +
            ", label=" + label +
            ", taskStartTime=" + taskStartTime +
            '}';
    }
}
