package com.waruguru.auth_service.dto.request;

import lombok.Data;

@Data
public class UserRegisterRequest {
    private String username;
    private String email;
    private  String password;
    private String confirmPassword;
}
