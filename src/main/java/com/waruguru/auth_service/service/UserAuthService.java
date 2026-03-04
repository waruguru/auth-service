package com.waruguru.auth_service.service;

import com.waruguru.auth_service.dto.request.UserLoginRequest;
import com.waruguru.auth_service.dto.request.UserRegisterRequest;
import com.waruguru.auth_service.dto.response.UserAuthResponse;

public interface UserAuthService {


    UserAuthResponse registerUser(UserRegisterRequest request);

    UserAuthResponse loginUser(UserLoginRequest request);
}
