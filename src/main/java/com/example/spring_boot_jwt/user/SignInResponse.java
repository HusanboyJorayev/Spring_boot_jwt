package com.example.spring_boot_jwt.user;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignInResponse {
    @NotBlank(message = "username cannot be null or empty")
    private String username;
    @NotBlank(message = "password cannot be null or empty")
    private String password;
}
