package com.triador.yourwayserver.controllers;

import com.triador.yourwayserver.dao.model.UserBook;
import com.triador.yourwayserver.dto.request.AddUserBookRequest;
import com.triador.yourwayserver.dto.response.ProfileResponse;
import com.triador.yourwayserver.service.ProfileService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping({"profiles"})
public class ProfileController {

    private ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping()
    public void addBookToProfile(@RequestBody AddUserBookRequest addUserBookRequest) {
        profileService.saveBook(addUserBookRequest);
    }

    @GetMapping(path = {"/{userId}"})
    public ProfileResponse getProfile(@PathVariable String userId) {
        return profileService.findById(Integer.parseInt(userId));
    }

    @DeleteMapping()
    public void deleteBook(@RequestParam("id") String userId,
                                     @RequestParam("id") String bookId) {
        profileService.deleteBook(Integer.parseInt(userId), Integer.parseInt(bookId));
    }
}
