package com.myProject.task_manager.dto;

import com.myProject.task_manager.entity.Role;

public class DtoUserResponse {
    private Integer id;
    private Role role;

    public DtoUserResponse(Integer id, Role role) {
        this.id = id;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }
}
