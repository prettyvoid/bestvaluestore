package com.abdalla.bestvaluestore.misc;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public HashMap<String, Object> hrsException(CustomException ex) {
        HashMap<String, Object> errorData = new HashMap<>();
        errorData.put("code", ex.getErrorCode());
        errorData.put("message", ex.getMessage());
        return errorData;
    }
}
