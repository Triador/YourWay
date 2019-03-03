package com.triador.yourwayserver.controllers;

import com.triador.yourwayserver.dao.model.Profile;
import com.triador.yourwayserver.dao.model.UserBook;
import com.triador.yourwayserver.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public void addBookToProfile(@RequestBody UserBook userBook) {
        profileService.save(userBook);
    }

    @GetMapping(path = {"/{userId}"})
    public Profile getProfile(@PathVariable String userId) {
        return profileService.findById(Integer.parseInt(userId));
    }

    @DeleteMapping()
    public void deleteBook(@RequestParam("userId") String userId,
                                     @RequestParam("bookId") String bookId) {
        profileService.deleteBook(Integer.parseInt(userId), Integer.parseInt(bookId));
    }
}
