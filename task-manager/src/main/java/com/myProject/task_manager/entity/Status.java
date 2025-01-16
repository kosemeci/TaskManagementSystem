package com.myProject.task_manager.entity;

import lombok.Getter;

@Getter
public enum Status {
    PENDING("Task is waiting to be started"),
    IN_PROGRESS("Task is currently in progress"),
    COMPLETED("Task has been completed"),
    FAILED("Task execution failed"),
    CANCELLED("Task has been cancelled");

    private final String description;

    Status(String description) {
        this.description = description;
    }
}
