package com.triador.yourwayserver.controllers;

import com.triador.yourwayserver.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping({"profiles"})
public class ProfileController {

    private ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping()
    public void addBookToProfile(
            @RequestBody int userId,
            @RequestBody int bookId) {
        profileService.save(userId, bookId);
    }
}
