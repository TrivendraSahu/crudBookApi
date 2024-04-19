package com.api.springcrudbook.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.springcrudbook.entity.User;
import com.api.springcrudbook.entity.UserLoginRequest;
import com.api.springcrudbook.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins="http://localhost:3001")
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody UserLoginRequest loginRequest) {
        User user = userService.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
        if (user != null) {
            if (user.getRole().equals("admin") || user.getRole().equals("regular")) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null); // Invalid role
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);// Invalid username or password
        }
       
    }
}
