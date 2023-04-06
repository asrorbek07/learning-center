package com.example.learningcenter.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class RoleEntity extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String name;
}
