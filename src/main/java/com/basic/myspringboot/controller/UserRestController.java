package com.basic.myspringboot.controller;

import com.basic.myspringboot.entity.User;
import com.basic.myspringboot.exception.BusinessException;
import com.basic.myspringboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserRestController {
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
        return getUserByEmail(email);
    }

    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email)   //Optional<User>
                .orElseThrow(() -> new BusinessException(email + " User Not Found", HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @PatchMapping("/{email}")
    public User updateUser(@PathVariable String email, @RequestBody User userDetail) {
        User user = getUserByEmail(email);
        user.setName(userDetail.getName());
        return userRepository.save(user);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<?> deleteUser(@PathVariable String email) {
        User user = getUserByEmail(email);
        userRepository.delete(user);
        return ResponseEntity.ok(email + " User Deleted OK!");
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }


}
