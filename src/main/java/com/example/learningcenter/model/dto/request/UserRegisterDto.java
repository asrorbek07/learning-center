package com.example.learningcenter.model.dto.request;

import com.example.learningcenter.model.entity.PermissionEntity;
import com.example.learningcenter.model.entity.RoleEntity;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.List;

@Data
public class UserRegisterDto {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private List<String> roleEntities;
    private List<String> permissionEntities;
}

