package com.flagcamp.gofitness.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.flagcamp.gofitness.model.Trainee;
import com.flagcamp.gofitness.model.Trainer;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.flagcamp.gofitness.service.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class SignInController {
    @Autowired
    private TraineeService traineeService;
    @Autowired
    private TrainerService trainerService;
    @Autowired
    private TokenService tokenService;

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public Map<String, Object> userLogin(@RequestBody Map<String, String> param, HttpServletRequest request) throws JSONException {
        Map<String, Object> map = new HashMap<>();
        String email = param.get("email");
        String password = param.get("password");
        if (email == null || email.length() == 0) {
            map.put("msg", "email cannot be empty!");
        } else if (password == null || password.length() == 0) {
            map.put("msg", "password cannot be empty!");
        } else if (traineeService.findTraineeByEmail(email) == null && trainerService.findTrainerByEmail(email) == null) {
            map.put("msg", "invalid email");
        } else if (traineeService.findTraineeByEmailAndPassword(email, password) == null && trainerService.findTrainerByEmailAndPassword(email, password) == null) {
            map.put("msg", "invalid password");
        } else {
            Trainer trainer = trainerService.findTrainerByEmailAndPassword(email, password);
            Trainee trainee = traineeService.findTraineeByEmailAndPassword(email, password);
            String token;
            Map<String, String> data = new HashMap<>();
            if (trainee != null) {
                token = tokenService.createToken(email, "trainee");
                data.put("email", email);
                data.put("name", traineeService.getFullName(email));
                data.put("role", "trainee");
                map.put("data", data);
                map.put("token", token);
            } else if (trainer != null) {
                token = tokenService.createToken(email, "trainer");
                data.put("email", email);
                data.put("name", trainerService.getFullName(email));
                data.put("role", "trainer");
                map.put("data", data);
                map.put("token", token);
            }
            map.put("status", HttpStatus.OK);
        }
        return map;
    }

}
