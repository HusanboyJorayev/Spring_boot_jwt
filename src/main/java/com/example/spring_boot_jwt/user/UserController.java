package com.example.spring_boot_jwt.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user/")
public class UserController {

    public ResponseEntity<String> user() {
        return ResponseEntity.ok("only user can uses");
    }

    public ResponseEntity<String> admin() {
        return ResponseEntity.ok("only admin can uses");
    }

    public ResponseEntity<String> userAndAdmin() {
        return ResponseEntity.ok("only userAndAdmin can uses");
    }
}
