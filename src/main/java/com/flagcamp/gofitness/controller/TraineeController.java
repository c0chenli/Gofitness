package com.flagcamp.gofitness.controller;


import com.flagcamp.gofitness.model.TraineeReservation;
import com.flagcamp.gofitness.model.Trainee;
import com.flagcamp.gofitness.model.Trainer;

import com.flagcamp.gofitness.service.TraineeService;
import com.flagcamp.gofitness.service.TrainerService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/trainee")
@CrossOrigin(origins = "http://localhost:3000")
public class TraineeController {

    @Autowired
    private TraineeService traineeService;
    @Autowired
    private TrainerService trainerService;
    private SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmm");

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public Trainee getTraineeInfo(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String traineeEmail = session.getAttribute("trainee").toString();
        return traineeService.findTraineeByEmail(traineeEmail);
    }

    @RequestMapping(value = "/getAllTrainer", method = RequestMethod.GET)
    public List<Trainer> getAllTrainer() {
        //@TODO add session
        return trainerService.getAllTrainers();
    }

    @PostMapping(value = "/reserve")
    public Map<String, String> reserveClass(@RequestBody Map<String, String> param, HttpServletRequest request) throws ParseException {
        Map<String, String> map = new HashMap<>();
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("trainee") == null) {
            map.put("status", "error");
            map.put("msg", "user login expired");
            return map;
        }
        String traineeEmail = session.getAttribute("trainee").toString();
        
        String trainerEmail = param.get("trainer_email");
        String startTime = param.get("start").replaceAll(",", "");
        String endTime = param.get("end").replaceAll(",", "");
        long start = sf.parse(startTime).getTime();
        long end = sf.parse(endTime).getTime();
        long time = 30 * 60 * 1000;
        List<TraineeReservation> reservations = new ArrayList<>();
        while (start < end) {
            TraineeReservation reservation = new TraineeReservation();
            reservation.setTrainerEmail(trainerEmail);
            //reservation.setTrainerName(trainerService.getFullName(trainerEmail));
            reservation.setStartTime(sf.format(start));
            reservation.setEndTime(sf.format((start + time)));
            reservations.add(reservation);
            start += time;
        }
        traineeService.addTraineeReservation(traineeEmail, reservations);
        map.put("status", "OK");
        map.put("msg", "add schedule successful.");
//        classService.reserveClass(traineeEmail, trainerEmail, startTime, endTime);
//        map.put("status", "OK");
//        map.put("msg", "You have reserved successfully.");
        return map;
    }

//    @PostMapping(value = "/cancel")
//    public Map<String, String> cancelClass(@RequestBody Map<String, String> param, HttpServletRequest request) throws JSONException {
//        Map<String, String> map = new HashMap<>();
//        HttpSession session = request.getSession();
//        String traineeEmail = session.getAttribute("trainee").toString();
//        if (traineeEmail == null || traineeEmail.length() == 0) {
//            map.put("status", "error");
//            map.put("msg", "user login expired");
//            return map;
//        }
//        String trainerEmail = param.get("trainer_email");
//        String startTime = param.get("start_time");
//        String endTime = param.get("end_time");
//        classService.cancelClass(traineeEmail, trainerEmail, startTime, endTime);
//        map.put("status", "OK");
//        map.put("msg", "You have cancelled the class.");
//        return map;
//    }

}
