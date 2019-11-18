package com.flagcamp.gofitness.controller;


import com.flagcamp.gofitness.model.Trainer;
import com.flagcamp.gofitness.service.ClassService;
import com.flagcamp.gofitness.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/trainer")
@CrossOrigin(origins = "http://localhost:3000")
public class TrainerController {

    @Autowired
    private TrainerService trainerService;

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public Trainer getUserByEmail(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String trainerEmail = session.getAttribute("trainer").toString();
        return trainerService.findTrainerByEmail(trainerEmail);
    }

}
