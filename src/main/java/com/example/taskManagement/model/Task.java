package com.example.taskManagement.model;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", taskName='" + taskName + '\'' +
                ", label=" + label +
                ", taskPriority=" + taskPriority +
                ", taskStartTime=" + taskStartTime +
                '}';
    }
}
