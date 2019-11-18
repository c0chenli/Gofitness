package com.flagcamp.gofitness.controller;

import java.util.*;


import javax.servlet.http.HttpServletRequest;

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
public class SignUpController {
	
	@Autowired
	private TraineeService traineeService;
	@Autowired
	private TrainerService trainerService;
    @Autowired
    private TraineeRepository traineeRepository;
    @Autowired
    private TrainerRepository trainerRepository;
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public Map<String, String> signup(@RequestBody Map<String, String> jsonParam) throws JSONException {
		Map<String, String> map = new HashMap<>();
		String firstName = jsonParam.get("firstname");
		String lastName = jsonParam.get("lastname");
		String email = jsonParam.get("email");
		Trainee exist1 = traineeRepository.findTraineeByEmail(email);
		if (exist1 != null) {
			map.put("status", "email already exist");
			return map;
		}
		Trainer exist2 = trainerRepository.findTrainerByEmail(email);
		if (exist2 != null) {
			map.put("status", "email already exist");
			return map;
		}
		String password = jsonParam.get("password");
		if (jsonParam.containsKey("categories")) {
			String categories = jsonParam.get("categories");
		}
		if (jsonParam.containsKey("role") && jsonParam.get("role").equals("trainee")) {
			Trainee trainee = new Trainee();
			trainee.setFirstname(firstName);
			trainee.setLastname(lastName);
			trainee.setEmail(email);
			trainee.setPassword(password);
			//TODO categories
			Set<String> set = new HashSet<>();
			set.add("Yoga");
			trainee.setCategories(set);
			traineeService.addTrainee(trainee);
			map.put("status", "good");
			return map;
		} else if (jsonParam.containsKey("role") && jsonParam.get("role").equals("trainer")) {
			Trainer trainer = new Trainer();
			trainer.setFirstname(firstName);
			trainer.setLastname(lastName);
			trainer.setEmail(email);
			trainer.setPassword(password);
			Set<String> set = new HashSet<>();
			set.add("Yoga");
			trainer.setCategories(set);
			trainerService.addTrainer(trainer);
			map.put("status", "good");
			return map;
		}
		map.put("status", "fail to register");
		return map;
		
	}
}
