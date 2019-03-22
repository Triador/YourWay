package com.triador.yourwayserver.controllers;

import com.triador.yourwayserver.dao.model.Marathon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping({"marathons"})
public class MarathonController {

    private MarathonService marathonService;

    @Autowired
    public MarathonController(MarathonService marathonService) {
        this.marathonService = marathonService;
    }

    @PostMapping
    public Marathon saveMarathon(@RequestBody Marathon marathon) {
        return marathonService.saveMarathon(marathon);
    }

    @GetMapping
    public List<Marathon> getMarathons() {
        return marathonService.getMarathons();
    }

    @GetMapping(path = {"/{id}"})
    public Marathon getMarathon(@PathVariable int id) {
        return marathonService.getMarathon(id);
    }
}
