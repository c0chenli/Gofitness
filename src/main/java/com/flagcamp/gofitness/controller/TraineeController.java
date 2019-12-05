package com.flagcamp.gofitness.controller;


import com.flagcamp.gofitness.model.TraineeReservation;
import com.flagcamp.gofitness.model.Schedule;
import com.flagcamp.gofitness.model.Trainee;
import com.flagcamp.gofitness.model.Trainer;
import com.flagcamp.gofitness.model.TrainerReservation;
import com.flagcamp.gofitness.service.TraineeService;
import com.flagcamp.gofitness.service.TrainerService;
//import com.opentok.exception.OpenTokException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import com.opentok.OpenTok;
//import com.opentok.Session;
//import com.opentok.TokenOptions;
//import com.opentok.Role;

@RestController
@RequestMapping("/trainee")
@CrossOrigin(origins = "http://localhost:3000")
public class TraineeController {

    @Autowired
    private TraineeService traineeService;
    @Autowired
    private TrainerService trainerService;

    private SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmm");

    private static final int apiKey = 46471732;
    private static final String apiSecret = "a6f2b1bf0fc61dc6ff7b831e5423bc75cfd74988";

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public Trainee getTraineeInfo(HttpServletRequest request) {
        String traineeEmail = (String) request.getAttribute("userEmail");
        return traineeService.findTraineeByEmail(traineeEmail);
    }

//    @RequestMapping(value = "/getVideoConfig", method = RequestMethod.GET)
//    public Map<String, Object> getVideoConfig(HttpServletRequest request) throws OpenTokException {
//        String traineeEmail = (String) request.getAttribute("userEmail");
//        Map<String, Object> map = new HashMap<>();
//        if (traineeEmail == null || traineeEmail.length() == 0) {
//            map.put("status", "error");
//            map.put("msg", "login expired, please signin again.");
//            return map;
//        }
//        // inside a class or method...
//        OpenTok opentok = new OpenTok(apiKey, apiSecret);
//        // A session that attempts to stream media directly between clients:
//        Session session = opentok.createSession();
//        String sessionId = session.getSessionId();
//        // Set some options in a token
//        String token = session.generateToken(new TokenOptions.Builder()
//                .role(Role.SUBSCRIBER)
//                .expireTime((System.currentTimeMillis() / 1000L) + (7 * 24 * 60 * 60)) // in one week
//                .data("email=" + traineeEmail)
//                .build());
//        map.put("status", "OK");
//        map.put("apiKey", apiKey);
//        map.put("sessionId", sessionId);
//        map.put("token", token);
//        return map;
//    }

    @RequestMapping(value = "/getAllTrainer", method = RequestMethod.GET)
    public List<Trainer> getAllTrainer(HttpServletRequest request) {
        String role = (String) request.getAttribute("role");
        if (!role.equals("trainee")) {
            return null;
        }
        return trainerService.getAllTrainers();
    }
    
    @RequestMapping(value = "/getTrainerSchedule", method = RequestMethod.GET)
    public List<Map<String, Object>> getTrainerSchedule(@RequestParam(value="trainer_email")String param, HttpServletRequest request) throws ParseException {
        String role = (String) request.getAttribute("role");
        if (!role.equals("trainee")) {
            return null;
        }
        String trainerEmail = param;
        List<Map<String, Object>> result = new ArrayList<>();
        Map<String, Object> map;
        Date date = new Date();
        String now = sf.format(date);
        List<TrainerReservation> reservations = new ArrayList<>();
        reservations.addAll(trainerService.getReservation(trainerEmail, now));
        long startTime;
        long endTime;
        for (TrainerReservation trainerReservation : reservations) {
            map = new HashMap<>();
            map.put("title", trainerReservation.getTraineeName());
            startTime = sf.parse(trainerReservation.getStartTime()).getTime();
            endTime = sf.parse(trainerReservation.getEndTime()).getTime();
            map.put("start", startTime);
            map.put("end", endTime);
            map.put("status", trainerReservation.getStatus());

            System.out.println(map.get("start"));
            result.add(map);
        }
        return result;
    }
    
    @RequestMapping(value = "/getTrainerAvailableTime", method = RequestMethod.GET)
    public List<Map<String, Object>> getTrainerAvailableTime(@RequestParam(value="trainer_email")String param, HttpServletRequest request) throws ParseException {
        String role = (String) request.getAttribute("role");
        if (!role.equals("trainee")) {
            return null;
        }
        String trainerEmail = param;
        Date date = new Date();
        String now = sf.format(date);
        List<Map<String, Object>> result = new ArrayList<>();
        Map<String, Object> map;
        long startTime;
        long endTime;
        List<Schedule> buffer = new ArrayList<>();
        buffer.addAll(trainerService.getSchedule(trainerEmail, now));
        for (Schedule schedule: buffer) {
            startTime = sf.parse(schedule.getStartTime()).getTime();
            endTime = sf.parse(schedule.getEndTime()).getTime();
        	map = new HashMap<>();
        	map.put("start", startTime);
        	map.put("end", endTime);
        	result.add(map);
        }
        return result;
    }

    @PostMapping(value = "/reserve")
    public Map<String, String> reserveClass(@RequestBody Map<String, String> param, HttpServletRequest request) throws ParseException {
        Map<String, String> map = new HashMap<>();
        String traineeEmail = (String) request.getAttribute("userEmail");

        //@Todo add title
        String title = param.get("title");
        String trainerEmail = param.get("trainer_email");
        String startTime = param.get("start").replaceAll(",", "");
        String endTime = param.get("end").replaceAll(",", "");

        //check whether the selected time is valid
        if (!traineeService.checkTraineeTime(traineeEmail, startTime, endTime)) {
            map.put("status", "error");
            map.put("msg", "selected time has collision with your reservations.");
            return map;
        }
        if (!traineeService.checkTrainerTime(trainerEmail, startTime, endTime)) {
            map.put("status", "error");
            map.put("msg", "selected time has collision with trainer's schedules.");
            return map;
        }

        String traineeName = traineeService.getFullName(traineeEmail);
        String trainerName = trainerService.getFullName(trainerEmail);
        traineeService.addTraineeReservation(title, traineeEmail, trainerEmail, trainerName, startTime, endTime);
        trainerService.addTrainerReservation(title, trainerEmail, traineeEmail, traineeName, startTime, endTime);
        map.put("status", "OK");
        map.put("msg", "add schedule successful.");
        return map;
    }
    
    @RequestMapping(value = "/getReservation", method = RequestMethod.GET)
    public List<Map<String, Object>> getReservation(HttpServletRequest request) throws ParseException {
    	//System.out.println("here");
        String traineeEmail = (String) request.getAttribute("userEmail");
        //System.out.println("No trainee");
        List<Map<String, Object>> result = new ArrayList<>();
        Map<String, Object> map;
        Date date = new Date();
        String now = sf.format(date);
        //List<TraineeReservation> reservations = new ArrayList<>();
        List<TraineeReservation> reservations = traineeService.getTraineeReservation(traineeEmail, now);
        //reservations.addAll(traineeService.getTraineeReservation(traineeEmail, now));
        long startTime;
        long endTime;
        for (TraineeReservation traineeReservation : reservations) {
            map = new HashMap<>();
            map.put("title", traineeReservation.getTitle());
            startTime = sf.parse(traineeReservation.getStartTime()).getTime();
            endTime = sf.parse(traineeReservation.getEndTime()).getTime();
            map.put("start", startTime);
            map.put("end", endTime);
            map.put("status", traineeReservation.getStatus());

            System.out.println(map.get("start"));
            result.add(map);
        }
        return result;
        //List<TraineeReservation> list = traineeService.getTraineeReservation(traineeEmail, now);
//        return list;
    }
    


    @PostMapping(value = "/cancelReservation")
    public Map<String, String> cancelReservations(@RequestBody Map<String, String> param, HttpServletRequest request) throws ParseException {
    	String traineeEmail = (String) request.getAttribute("userEmail");
    	Map<String, String> map = new HashMap<>();
	  String startTime = param.get("start").replaceAll(",", "");
	  //String endTime = param.get("end").replaceAll(",", "");
	  long start = sf.parse(startTime).getTime();
	  traineeService.cancelReservation(traineeEmail, start);
	  map.put("status", "success");
      return map;
    }
 
    
    
    

}
