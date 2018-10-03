package com.triador.yourwayserver.controllers;

import com.triador.yourwayserver.dao.model.User;
import com.triador.yourwayserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping({"users"})
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/signup")
    public User create(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping(path = {"/{id}"})
    public User findOne(@PathVariable("id") int id) {
        return userService.findById(id);
    }

    @DeleteMapping(path = {"/{id}"})
    public User delete(@PathVariable("id") int id) {
        return userService.delete(id);
    }

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

}
