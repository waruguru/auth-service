package com.waruguru.auth_service.serviceImpl;

import com.waruguru.auth_service.dto.request.UserLoginRequest;
import com.waruguru.auth_service.dto.request.UserRegisterRequest;
import com.waruguru.auth_service.dto.response.UserAuthResponse;
import com.waruguru.auth_service.entity.Role;
import com.waruguru.auth_service.entity.UserEntity;
import com.waruguru.auth_service.repository.UserRepository;
import com.waruguru.auth_service.service.UserAuthService;
import com.waruguru.auth_service.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthServiceImpl implements UserAuthService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public UserAuthResponse registerUser(UserRegisterRequest request) {
        if(repository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        if (!request.getPassword().equals(request.getConfirmPassword())){
            throw new IllegalArgumentException("Passwords do not match!");
        }
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.FARMER); // default role

        repository.save(user);

        String token = jwtUtil.generateToken(user);
        return new UserAuthResponse(token);
    }

    @Override
    public UserAuthResponse loginUser(UserLoginRequest request) {
        UserEntity user = repository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username"));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(user);
        return new UserAuthResponse(token);
    }

}
