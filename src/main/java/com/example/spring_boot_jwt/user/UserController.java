package com.example.spring_boot_jwt.user;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user/")
public class UserController {

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/getUser")
    public ResponseEntity<String> user() {
        return ResponseEntity.ok("only user can uses");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getAdmin")
    public ResponseEntity<String> admin() {
        return ResponseEntity.ok("only admin can uses");
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping("/getAdminAndUser")
    public ResponseEntity<String> userAndAdmin() {
        return ResponseEntity.ok("only userAndAdmin can uses");
    }
}
