package com.example.learningcenter.repository;

import com.example.learningcenter.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    public Optional<UserEntity> findByUsername(String username);
}
