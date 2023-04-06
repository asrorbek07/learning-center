package com.example.learningcenter.common;

import com.example.learningcenter.model.entity.PermissionEntity;
import com.example.learningcenter.model.entity.RoleEntity;
import com.example.learningcenter.model.entity.UserEntity;
import com.example.learningcenter.repository.PermissionRepository;
import com.example.learningcenter.repository.RoleRepository;
import com.example.learningcenter.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private  final UserRepository userRepository;
    private  final RoleRepository roleRepository;
    private  final PermissionRepository permissionRepository;

    @Override
    public void run(String... args) throws Exception {
        List<PermissionEntity> all = permissionRepository.findAll();
        if (all.isEmpty()){
            permissionRepository.saveAll(List.of(
                    new PermissionEntity("CREATE"),
                    new PermissionEntity("READ"),
                    new PermissionEntity("UPDATE"),
                    new PermissionEntity("DELETE")
                    ));
        }
        if (roleRepository.findAll().isEmpty()){
            roleRepository.saveAll(List.of(
                    new RoleEntity("SUPER_ADMIN"),
                    new RoleEntity("ADMIN"),
                    new RoleEntity("TEACHER"),
                    new RoleEntity("USER")
            ));
        }
        if (!userRepository.findByUsername("superAdmin").isPresent()){
            UserEntity userEntity = new UserEntity("superAdmin", "superAdmin", "Asrorbek", "Obidjonov",
                    List.of(roleRepository.findByName("SUPER_ADMIN").get()),
                    permissionRepository.findByNameIn(List.of("CREATE", "READ", "UPDATE", "DELETE")).get());
            userRepository.save(userEntity);
        }
    }
}
