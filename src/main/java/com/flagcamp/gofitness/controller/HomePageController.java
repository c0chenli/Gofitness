package com.flagcamp.gofitness.controller;

import com.flagcamp.gofitness.model.Trainer;
import com.flagcamp.gofitness.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class HomePageController {

    @Autowired
    private TrainerService trainerService;

    @RequestMapping(value = "/getAllTrainerDemo", method = RequestMethod.GET)
    public List<Map<String, Object>> getAllTrainerDemo() {
        List<Map<String, Object>> result = new ArrayList<>();
        Map<String, Object> map;
        List<Trainer> trainers = trainerService.getAllTrainers();
        for (Trainer trainer : trainers) {
            map = new HashMap<>();
            map.put("name", trainerService.getFullName(trainer.getEmail()));
            map.put("categories", trainer.getCategories());
            map.put("email", trainer.getEmail());
            result.add(map);
        }
        return result;
    }


}
