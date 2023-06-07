package com.chanwoo.aws.controller;

import com.chanwoo.aws.entity.User;
import com.chanwoo.aws.exception.ResourceNotFound;
import com.chanwoo.aws.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")

public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    // get all users
    @GetMapping
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    // get user by id
    @GetMapping("/{id}")
    public User getUserById(@PathVariable(value = "id") Long userId) {
        return this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFound("User not found with id " + userId));
    }

    // create user
    @PostMapping
    public User createUser(@RequestBody User user){
        return this.userRepository.save(user);
    }

    // update user
    @PutMapping("/{id}")
    public User updateUser(@PathVariable(value = "id")Long userId, @RequestBody User updateUser){
        User user = this.userRepository.findById(userId)
                .orElseThrow(() ->new ResourceNotFound("User not found with id " + userId));

        if (updateUser.getFirstName() != null){
            user.setFirstName(updateUser.getFirstName());
        }
        if (updateUser.getLastName() != null){
            user.setLastName(updateUser.getLastName());
        }
        if (updateUser.getEmail() != null) {
            user.setEmail(updateUser.getEmail());
        }

        userRepository.save(user);
        return user;
    }

    // delete user by id
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUserById(@PathVariable(value = "id") Long userId){
        User user = this.userRepository.findById(userId)
                .orElseThrow(() ->new ResourceNotFound("User not found with id " + userId));

        this.userRepository.delete(user);
        return ResponseEntity.ok().build();
    }
}
