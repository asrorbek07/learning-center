package com.example.learningcenter.service;

import com.example.learningcenter.common.ResponseMessage;
import com.example.learningcenter.common.exception.PermissionNotFoundException;
import com.example.learningcenter.common.exception.RoleNotFoundException;
import com.example.learningcenter.model.dto.request.UserRegisterDto;
import com.example.learningcenter.model.dto.response.ApiResponse;
import com.example.learningcenter.model.entity.PermissionEntity;
import com.example.learningcenter.model.entity.RoleEntity;
import com.example.learningcenter.model.entity.UserEntity;
import com.example.learningcenter.repository.PermissionRepository;
import com.example.learningcenter.repository.RoleRepository;
import com.example.learningcenter.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements BaseService<UserRegisterDto, ApiResponse>{
    private final PermissionRepository permissionRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Override
    public ApiResponse create(UserRegisterDto userRegisterDto) {
        List<RoleEntity> roleEntities = checkRoles(userRegisterDto.getRoleEntities());
        List<PermissionEntity> permissionEntities = checkPermissions(userRegisterDto.getPermissionEntities());
        UserEntity userEntity = new UserEntity(userRegisterDto.getUsername(), userRegisterDto.getPassword(), userRegisterDto.getFirstName(), userRegisterDto.getLastName(), roleEntities, permissionEntities);
        userRepository.save(userEntity);
        return new ApiResponse(userEntity.getUserResponseDto(),ResponseMessage.SUCCESS.getMessage());
    }

    private List<RoleEntity> checkRoles(List<String> roles) {
        if (roles==null) return List.of(roleRepository.findByName("USER").get());
        return roles.stream().map(s -> roleRepository.findByName(s).orElseThrow(() ->
                new RoleNotFoundException(ResponseMessage.ERROR_ROLE_NOT_FOUND.getMessage()))).toList();
    }
    private List<PermissionEntity> checkPermissions(List<String> permissions){
        if (permissions==null) return permissionRepository.findByNameIn(List.of("READ")).get();
        return permissions.stream().map(p -> permissionRepository.findByName(p).orElseThrow(() ->
                new PermissionNotFoundException(ResponseMessage.ERROR_PERMISSION_NOT_FOUND.getMessage()))).toList();
    }

    @Override
    public ApiResponse get(Integer id) {
        return null;
    }

    @Override
    public ApiResponse getAll() {
        return null;
    }

    @Override
    public ApiResponse update(UserRegisterDto userRegisterDto, Integer id) {
        return null;
    }

    @Override
    public ApiResponse delete(Integer id) {
        return null;
    }
}
