package com.chemax.project.dto;

import com.chemax.project.entities.Role;
import lombok.Data;

import java.util.Set;

@Data
public class UserProfile {
    private Integer id;
    private String username;
    private String password;
    private Set<Role> roles;
}
