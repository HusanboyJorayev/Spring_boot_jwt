package com.example.spring_boot_jwt.user;

import com.example.spring_boot_jwt.config.JWTTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/auth/")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository repository;
    private final UserMapper userMapper;
    private final JWTTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/signUp")
    public ResponseEntity<UserDto> signUp(@RequestBody UserDto dto) {
        User user = this.userMapper.toEntity(dto);
        user.setCreatedAt(LocalDateTime.now());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        UserDto userDto = this.userMapper.toDto(user);
        this.repository.save(user);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/signIn")
    public ResponseEntity<String> signIn(@RequestBody SignInResponse response) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(response.getUsername(), response.getPassword());
        authenticationManager.authenticate(authenticationToken);
        String token = this.jwtTokenUtil.generateToken(response.getUsername());
        return ResponseEntity.ok(token);
    }
}
