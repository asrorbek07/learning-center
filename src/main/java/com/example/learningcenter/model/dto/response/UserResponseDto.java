package com.example.learningcenter.model.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;
import java.util.List;
@Data
@Builder
public class UserResponseDto {
    private Integer id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private List<String> roleEntities;
    private List<String> permissionEntities;
    private Boolean isAccountNonExpired;
    private Boolean isAccountNonLocked;
    private Boolean isCredentialsNonExpired;
    private Boolean isEnabled;
}
