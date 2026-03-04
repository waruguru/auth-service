package com.waruguru.auth_service.controller;

import com.waruguru.auth_service.dto.request.UserLoginRequest;
import com.waruguru.auth_service.dto.request.UserRegisterRequest;
import com.waruguru.auth_service.dto.response.ApiResponse;
import com.waruguru.auth_service.dto.response.UserAuthResponse;
import com.waruguru.auth_service.service.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserAuthController {
    private final UserAuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserAuthResponse>> register(@RequestBody UserRegisterRequest request) {
        String token = String.valueOf(authService.registerUser(request));
        UserAuthResponse response = new UserAuthResponse(token);

        ApiResponse<UserAuthResponse> userAuthResponseApiResponse = new ApiResponse<>(
                HttpStatus.CREATED.value(),
                 "Registration successful",
                null,
                response);

        return ResponseEntity.ok(userAuthResponseApiResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<UserAuthResponse> login(@RequestBody UserLoginRequest request) {
        return ResponseEntity.ok(authService.loginUser(request));
    }
}
