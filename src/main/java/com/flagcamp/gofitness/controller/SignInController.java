package com.flagcamp.gofitness.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.flagcamp.gofitness.model.*;
import com.flagcamp.gofitness.repository.*;
import com.flagcamp.gofitness.service.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class SignInController {
	@Autowired
	private TraineeService traineeService;
	@Autowired
	private TrainerService trainerService;
    @Autowired
    private TraineeRepository traineeRepository;
    @Autowired
    private TrainerRepository trainerRepository;
    
    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public Map<String, String> userLogin(@RequestBody Map<String, String> param, HttpServletRequest request) throws JSONException {
        Map<String, String> map = new HashMap<>();
        HttpSession session = request.getSession();
        String email = param.get("email");
        String password = param.get("password");
        boolean isTrainee = false;
        boolean isTrainer = false;
        
        if (email == null || email.length() == 0) {
            map.put("status", "email cannot be empty!");
        } else if (password == null || password.length() == 0) {
            map.put("status", "password cannot be empty!");
        } else if (traineeRepository.findTraineeByEmail(email) == null && trainerRepository.findTrainerByEmail(email) == null) {
            map.put("status", "invalid email");
        } else if (traineeRepository.findTraineeByEmailAndPassword(email, password) == null && trainerRepository.findTrainerByEmailAndPassword(email, password) == null) {
            map.put("status", "invalid password");
        } else {
        	if (traineeRepository.findTraineeByEmailAndPassword(email, password) != null) {
                session.setAttribute("trainee", email);
                //set session duration 60 minutes.
                session.setMaxInactiveInterval(3600);
                map.put("status", "OK");
                map.put("role", "trainee");
        	} else if (trainerRepository.findTrainerByEmailAndPassword(email, password) != null) {
                session.setAttribute("trainer", email);
                //set session duration 60 minutes.
                session.setMaxInactiveInterval(3600);
                map.put("status", "OK");
                map.put("role", "trainer");
        	}
        }
        return map;
    }

}
