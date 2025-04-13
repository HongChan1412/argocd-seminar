package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<Users> findAll() {
        return userRepository.findAll();
    }

    @PostMapping
    public Users save(@RequestBody Users users) {
        return userRepository.save(users);
    }
}
