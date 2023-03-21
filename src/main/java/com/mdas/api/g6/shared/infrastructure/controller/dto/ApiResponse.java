package com.mdas.api.g6.shared.infrastructure.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class ApiResponse<T> {
    private HttpStatus status;
    private String message;
    private T data;
}
