package com.waruguru.auth_service.exception;

import com.waruguru.auth_service.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<?>> handleIllegalArgs(IllegalArgumentException ex) {
        ApiResponse<?> body = new ApiResponse<>(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                null,
                null
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<?>> handleRuntimeException(RuntimeException ex) {
        ApiResponse<?> body = new ApiResponse<>(
                HttpStatus.FORBIDDEN.value(),
                ex.getMessage(),
                null,
                null
        );
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleGenericException(Exception ex) {
        ApiResponse<?> body = new ApiResponse<>(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Unexpected error occurred",
                null,
                null
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<ApiResponse<Object>> handleBadRequest(
//            IllegalArgumentException ex) {
//
//        ApiResponse<Object> response =
//                new ApiResponse<>(
//                        HttpStatus.BAD_REQUEST.value(),
//                        "Validation failed",
//                        ex.getMessage(),
//                        null
//                );
//
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(BadCredentialsException.class)
//    public ResponseEntity<ApiResponse<Object>> handleUnauthorized(
//            BadCredentialsException ex) {
//
//        ApiResponse<Object> response =
//                new ApiResponse<>(
//                        HttpStatus.UNAUTHORIZED.value(),
//                        "Authentication failed",
//                        "Invalid email or password",
//                        null
//                );
//
//        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
//    }
//
//    @ExceptionHandler(AccessDeniedException.class)
//    public ResponseEntity<ApiResponse<Object>> handleForbidden(
//            AccessDeniedException ex) {
//
//        ApiResponse<Object> response =
//                new ApiResponse<>(
//                        HttpStatus.FORBIDDEN.value(),
//                        "Access denied",
//                        ex.getMessage(),
//                        null
//                );
//
//        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ApiResponse<Object>> handleGeneral(
//            Exception ex) {
//
//        ApiResponse<Object> response =
//                new ApiResponse<>(
//                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
//                        "Something went wrong",
//                        ex.getMessage(),
//                        null
//                );
//
//        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
