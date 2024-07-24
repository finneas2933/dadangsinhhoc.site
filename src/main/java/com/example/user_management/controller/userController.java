package com.example.user_management.controller;

import com.example.user_management.models.User;
import com.example.user_management.models.responseObject;
import com.example.user_management.repositories.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class userController {
    @Autowired
    private userRepository userRepo;
    @Autowired
    private com.example.user_management.services.userService userService;

    @GetMapping("/getAllUser")
    public responseObject getAllUser() {
        List<User> users = userService.getAllUsers();
        return new responseObject("200", "Query All Users Success", users);
    }

    @PostMapping("/insertUser")
    ResponseEntity<responseObject> insertUser(@RequestBody User newUser) {

        return ResponseEntity.status(HttpStatus.OK).body(
                new responseObject("200", "Query All Users Success", userRepo.save(newUser))
        );
    }
}
