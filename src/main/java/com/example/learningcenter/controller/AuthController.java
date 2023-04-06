package com.example.learningcenter.controller;

import com.example.learningcenter.model.dto.request.UserRegisterDto;
import com.example.learningcenter.model.dto.response.ApiResponse;
import com.example.learningcenter.model.dto.response.UserResponseDto;
import com.example.learningcenter.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth/")
public class AuthController {
private final UserService userService;
@PostMapping("register")
    public ApiResponse<UserResponseDto> register(
        @RequestBody UserRegisterDto userRegisterDto
        ){
    return userService.create(userRegisterDto);
}
}
