package com.energy.users.controller;

import com.energy.users.model.*;
import com.energy.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ResponseBody
    public UserLoginResponse loginUser(@Valid @RequestBody UserLogin userLogin) {
        return userService.userLogin(userLogin);
    }

    @PostMapping
    @ResponseBody
    public UserResponse postUser(@Valid @RequestBody User user) {
        return userService.postUser(user);
    }

    @GetMapping("/{userId}/info")
    @ResponseBody
    public UserInfo getUserInfo(@PathVariable() String userId) {
        return userService.getUserInfo(userId);
    }

    @GetMapping()
    @ResponseBody
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    @ResponseBody
    public UserResponse getUser(@PathVariable() String userId) {
        return userService.getUser(userId);
    }
}

