package com.questApp.questApp.controller;

import com.questApp.questApp.entity.User;
import com.questApp.questApp.exception.UserNotFoundException;
import com.questApp.questApp.repository.UserRepository;
import com.questApp.questApp.response.UserResponse;
import com.questApp.questApp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.save(user);
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id){
        User user = userService.getUserById(id);
        if(user == null) {
            throw new UserNotFoundException();
        }
        return new UserResponse(user);    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User newUser){
        return userService.updateUser(id, newUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteById(id);
    }

    @GetMapping("/activity/{userId}")
    public List<Object> getUserActivity(@PathVariable Long userId){
        return userService.getUserActivity(userId);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private ResponseEntity<String> handleUserNotFound(UserNotFoundException ex){
        String message = "User not found.";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }
}
