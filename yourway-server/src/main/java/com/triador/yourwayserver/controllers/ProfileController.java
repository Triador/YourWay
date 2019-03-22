package com.triador.yourwayserver.controllers;

import com.triador.yourwayserver.dto.response.ProfileResponse;
import com.triador.yourwayserver.dto.request.AddUserBookRequest;
import com.triador.yourwayserver.service.ProfileService;
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
    public void addBookToProfile(@RequestBody AddUserBookRequest userBook) {
        profileService.save(userBook);
    }

    @GetMapping(path = {"/{userId}"})
    public ProfileResponse getProfile(@PathVariable String userId) {
        return profileService.findById(Integer.parseInt(userId));
    }

    @DeleteMapping()
    public void deleteBook(@RequestParam("id") String userId,
                                     @RequestParam("bookId") String bookId) {
        profileService.deleteBook(Integer.parseInt(userId), Integer.parseInt(bookId));
    }
}
