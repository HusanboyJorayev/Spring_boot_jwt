package com.example.spring_boot_jwt;

import com.example.spring_boot_jwt.user.Role;
import com.example.spring_boot_jwt.user.User;
import com.example.spring_boot_jwt.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@SpringBootApplication
public class SpringBootJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootJwtApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(UserRepository repository, PasswordEncoder passwordEncoder) {
        return args -> {
            User admin = User.builder()
                    .username("admin123")
                    .password(passwordEncoder.encode("123"))
                    .email("qwe@gmail.com")
                    .role(Role.ADMIN)
                    .firstname("Husanboy")
                    .lastname("Jorayev")
                    .createdAt(LocalDateTime.now())
                    .build();

            User user = User.builder()
                    .username("user123")
                    .password(passwordEncoder.encode("123"))
                    .email("qwe@gmail.com")
                    .role(Role.USER)
                    .firstname("Husanboy")
                    .lastname("Jorayev")
                    .createdAt(LocalDateTime.now())
                    .build();
            repository.save(admin);
            repository.save(user);
        };
    }
}
