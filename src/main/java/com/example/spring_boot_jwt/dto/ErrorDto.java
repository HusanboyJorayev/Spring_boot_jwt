package com.example.spring_boot_jwt.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDto {
    private String path;
    private String message;
    private int code;

    private LocalDateTime createdAt;

    public ErrorDto(String path, String message, int code) {
        this.path = path;
        this.message = message;
        this.code = code;
        this.createdAt = LocalDateTime.now();
    }
}
