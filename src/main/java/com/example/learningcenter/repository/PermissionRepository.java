package com.example.learningcenter.repository;

import com.example.learningcenter.model.entity.PermissionEntity;
import com.example.learningcenter.model.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PermissionRepository extends JpaRepository<PermissionEntity,Integer> {
    Optional<PermissionEntity> findByName(String name);
    Optional<List<PermissionEntity>> findByNameIn(List<String> names);
}
