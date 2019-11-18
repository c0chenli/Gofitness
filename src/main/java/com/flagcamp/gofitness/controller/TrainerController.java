package com.flagcamp.gofitness.controller;


import com.flagcamp.gofitness.model.Trainer;
import com.flagcamp.gofitness.service.ClassService;
import com.flagcamp.gofitness.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainer")
@CrossOrigin(origins = "http://localhost:3000")
public class TrainerController {

    @Autowired
    private TrainerService trainerService;

    @RequestMapping(value = "/{email}", method = RequestMethod.GET)
    public Trainer getUserByEmail(@PathVariable String email) {
        return trainerService.findTrainerByEmail(email);
    }

    @RequestMapping(value = "/getAllTrainer", method = RequestMethod.GET)
    public List<Trainer> getAllTrainer() {
        return trainerService.getAllTrainers();
    }



}
