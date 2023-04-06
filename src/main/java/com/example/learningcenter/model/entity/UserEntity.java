package com.example.learningcenter.model.entity;

import com.example.learningcenter.model.dto.response.UserResponseDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements UserDetails, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String firstName;
    private String lastName;
    @ManyToMany
    private List<RoleEntity> roleEntities;
    @ManyToMany
    private List<PermissionEntity> permissionEntities;
    private Boolean isAccountNonExpired=true;
    private Boolean isAccountNonLocked=true;
    private Boolean isCredentialsNonExpired=true;
    private Boolean isEnabled=true;


    public UserEntity(String username, String password, String firstName, String lastName, List<RoleEntity> roleEntities, List<PermissionEntity> permissionEntities) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roleEntities = roleEntities;
        this.permissionEntities = permissionEntities;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> list =null;
        for (RoleEntity roleEntity : roleEntities) {
            list.add(new SimpleGrantedAuthority("Role_"+roleEntity.getName()));
        }
        for (PermissionEntity permissionEntity : permissionEntities) {
            list.add(new SimpleGrantedAuthority(permissionEntity.getName()));
        }
        return list;
    }
    public UserResponseDto getUserResponseDto(){
        return UserResponseDto.builder()
                .id(this.id)
                .username(this.username)
                .password(this.password)
                .firstName(this.firstName)
                .lastName(this.lastName)
                .roleEntities(this.getRolesList())
                .permissionEntities(this.getPermissionsList())
                .isAccountNonExpired(this.isAccountNonExpired)
                .isAccountNonLocked(this.isAccountNonLocked)
                .isCredentialsNonExpired(this.isCredentialsNonExpired)
                .isEnabled(this.isEnabled)
                .build();
    }
    public List<String> getRolesList(){
        return roleEntities.stream().map(roleEntity -> roleEntity.getName()).toList();
    }
    public List<String> getPermissionsList(){
        return permissionEntities.stream().map(permissionEntity -> permissionEntity.getName()).toList();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
