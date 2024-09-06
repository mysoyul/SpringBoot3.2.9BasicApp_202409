package com.basic.myspringboot.controller;

import com.basic.myspringboot.entity.User;
import com.basic.myspringboot.exception.BusinessException;
import com.basic.myspringboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository userRepository;

    //Constructor Injection
//    public UserController(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    @PostMapping
    public User create(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/{email}")
    public User getUser(@PathVariable String email){
        return userRepository.findByEmail(email)   //Optional<User>
                .orElseThrow(() -> new BusinessException(email + " User Not Found", HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<User> getUsers() {
        return userRepository.findAll();
    }

}
