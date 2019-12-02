package com.flagcamp.gofitness.controller;

import java.util.*;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.flagcamp.gofitness.model.*;
import com.flagcamp.gofitness.service.*;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class SignUpController {
	
	@Autowired
	private TraineeService traineeService;
	@Autowired
	private TrainerService trainerService;
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public Map<String, String> signUp(@RequestBody Map<String, Object> jsonParam) throws JSONException {
		Map<String, String> map = new HashMap<>();
		String firstName = (String) jsonParam.get("firstname");
		String lastName = (String) jsonParam.get("lastname");
		String email = (String) jsonParam.get("email");
		List<String> categories = new ArrayList<>();
		Set<String> categorySet = new HashSet<>();
		Trainee exist1 = traineeService.findTraineeByEmail(email);
		if (exist1 != null) {
			map.put("status", "email already exist");
			return map;
		}
		Trainer exist2 = trainerService.findTrainerByEmail(email);
		if (exist2 != null) {
			map.put("status", "email already exist");
			return map;
		}
		String password = (String) jsonParam.get("password");
		if (jsonParam.containsKey("categories")) {
			categories = (List<String>) jsonParam.get("categories");
		}

		if (jsonParam.containsKey("role") && jsonParam.get("role").equals("trainee")) {
			Trainee trainee = new Trainee();
			trainee.setFirstname(firstName);
			trainee.setLastname(lastName);
			trainee.setEmail(email);
			trainee.setPassword(password);
			traineeService.addNewTrainee(trainee);
			map.put("status", "OK");
			return map;
		} else if (jsonParam.containsKey("role") && jsonParam.get("role").equals("trainer")) {
			Trainer trainer = new Trainer();
			trainer.setFirstname(firstName);
			trainer.setLastname(lastName);
			trainer.setEmail(email);
			trainer.setPassword(password);
			categorySet.addAll(categories);
			trainer.setCategories(categorySet);
			trainerService.addNewTrainer(trainer);
			map.put("status", "OK");
			return map;
		}
		map.put("status", "fail to register");
		return map;
		
	}
}
