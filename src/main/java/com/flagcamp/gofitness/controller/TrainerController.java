package com.flagcamp.gofitness.controller;


import com.flagcamp.gofitness.model.Trainer;
import com.flagcamp.gofitness.repository.TrainerRepository;
import com.flagcamp.gofitness.service.TrainerService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/trainer")
@CrossOrigin(origins = "http://localhost:3000")
public class TrainerController {

    @Autowired
    private TrainerRepository trainerRepository;

    @RequestMapping(value = "/{email}", method = RequestMethod.GET)
    public Trainer getUserByEmail(@PathVariable String email) {
        return trainerRepository.findTrainerByEmail(email);
    }

    @RequestMapping(value = "/getAllTrainer", method = RequestMethod.GET)
    public List<Trainer> getAllTrainer() {
        return trainerRepository.findAll();
    }

    @PostMapping(value = "/register")
    @ResponseBody
    public Map<String, String> userRegister(@RequestBody @Valid Trainer trainer, BindingResult result) throws JSONException {
        Map<String, String> map = new HashMap<>();
        if (result.hasErrors()) {
            FieldError error = (FieldError) result.getAllErrors().get(0);
            map.put("status", String.valueOf(error.getDefaultMessage()));
            return map;
        }
        Trainer exist = trainerRepository.findTrainerByEmail(trainer.getEmail());
        if (exist != null) {
            map.put("status", "email already exist");
            return map;
        }
        trainerRepository.save(trainer);
        map.put("status", "OK");
        return map;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, String> userLogin(@RequestBody Map<String, String> param, HttpServletRequest request) throws JSONException {
        Map<String, String> map = new HashMap<>();
        HttpSession session = request.getSession();
        String email = param.get("email");
        String password = param.get("password");
        if (email == null || email.length() == 0) {
            map.put("status", "email cannot be empty!");
        } else if (password == null || password.length() == 0) {
            map.put("status", "password cannot be empty!");
        } else if (trainerRepository.findTrainerByEmail(email) == null) {
            map.put("status", "invalid email");
        } else if (trainerRepository.findTrainerByEmailAndPassword(email, password) == null) {
            map.put("status", "invalid password");
        } else {
            session.setAttribute("trainee", email);
            //set session duration 60 minutes.
            session.setMaxInactiveInterval(3600);
            map.put("status", "OK");
            map.put("role", "trainer");
        }
        return map;
    }


}
