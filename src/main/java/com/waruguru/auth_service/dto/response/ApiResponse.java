package com.waruguru.auth_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ApiResponse <T>{
    private int responseCode;
    private String responseMessage;
    private String error;
    private T body;

}
