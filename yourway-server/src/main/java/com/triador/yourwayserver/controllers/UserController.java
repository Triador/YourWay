package com.triador.yourwayserver.controllers;

import com.triador.yourwayserver.dao.model.User;
import com.triador.yourwayserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping({"users"})
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/signup")
    public User createUser(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping(path = {"/{userId}"})
    public User getUser(@PathVariable("id") int userId) {
        return userService.findById(userId);
    }

}
