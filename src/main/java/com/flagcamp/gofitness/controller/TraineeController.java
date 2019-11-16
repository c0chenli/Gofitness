package com.flagcamp.gofitness.controller;


import com.flagcamp.gofitness.model.Trainee;
import com.flagcamp.gofitness.repository.TraineeRepository;
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
import java.util.Map;
@RestController
@RequestMapping("/trainee")
@CrossOrigin(origins = "http://localhost:3000")
public class TraineeController {

    @Autowired
    private TraineeRepository traineeRepository;

    @RequestMapping(value = "/{email}", method = RequestMethod.GET)
    public Trainee getCoachByEmail(@PathVariable String email) {
        return traineeRepository.findTraineeByEmail(email);
    }

    @PostMapping(value = "/register")
    @ResponseBody
    public Map<String, String> userRegister(@RequestBody @Valid Trainee trainee, BindingResult result) throws JSONException {
        Map<String, String> map = new HashMap<>();
        if (result.hasErrors()) {
            FieldError error = (FieldError) result.getAllErrors().get(0);
            map.put("status", String.valueOf(error.getDefaultMessage()));
            return map;
        }
        Trainee exist = traineeRepository.findTraineeByEmail(trainee.getEmail());
        if (exist != null) {
            map.put("status", "email already exist");
            return map;
        }
        traineeRepository.save(trainee);
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
        } else if (traineeRepository.findTraineeByEmail(email) == null) {
            map.put("status", "invalid email");
        } else if (traineeRepository.findTraineeByEmailAndPassword(email, password) == null) {
            map.put("status", "invalid password");
        } else {
            session.setAttribute("trainee", email);
            //set session duration 60 minutes.
            session.setMaxInactiveInterval(3600);
            map.put("status", "OK");
            map.put("role", "trainee");
        }
        return map;
    }

}
