package com.flagcamp.gofitness.controller;

import com.flagcamp.gofitness.model.Schedule;
import com.flagcamp.gofitness.model.Trainer;
import com.flagcamp.gofitness.model.TrainerReservation;
import com.flagcamp.gofitness.service.TrainerService;
//import com.opentok.OpenTok;
//import com.opentok.Role;
//import com.opentok.Session;
//import com.opentok.TokenOptions;
//import com.opentok.exception.OpenTokException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/trainer")
@CrossOrigin(origins = "http://localhost:3000")
public class TrainerController {

    @Autowired
    private TrainerService trainerService;
    private SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmm");
    private static final long TWENTY_FOUR_HOUR = 60 * 24 * 60 * 1000;
    private static final int apiKey = 46471732;
    private static final String apiSecret = "a6f2b1bf0fc61dc6ff7b831e5423bc75cfd74988";

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public Trainer getUserByEmail(HttpServletRequest request) {
        String trainerEmail = (String) request.getAttribute("userEmail");
        return trainerService.findTrainerByEmail(trainerEmail);
    }

//    @RequestMapping(value = "/getVideoConfig", method = RequestMethod.GET)
//    public Map<String, Object> getVideoConfig(HttpServletRequest request) throws OpenTokException {
//        String trainerEmail = (String) request.getAttribute("userEmail");
//        Map<String, Object> map = new HashMap<>();
//        if (trainerEmail == null || trainerEmail.length() == 0) {
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
//                .role(Role.PUBLISHER)
//                .expireTime((System.currentTimeMillis() / 1000L) + (7 * 24 * 60 * 60)) // in one week
//                .data("email=" + trainerEmail)
//                .build());
//        map.put("status", "OK");
//        map.put("apiKey", apiKey);
//        map.put("sessionId", sessionId);
//        map.put("token", token);
//        return map;
//    }

    @PostMapping(value = "/setSchedule")
    public Map<String, String> setSchedule(@RequestBody Map<String, String> param, HttpServletRequest request) throws ParseException {
        Map<String, String> map = new HashMap<>();
        String trainerEmail = (String) request.getAttribute("userEmail");
        String startTime = param.get("start").replaceAll(",", "");
        String endTime = param.get("end").replaceAll(",", "");
        long start = sf.parse(startTime).getTime();
        long end = sf.parse(endTime).getTime();
        //24 hours
        int numOfDays = (int) ((end - start) / TWENTY_FOUR_HOUR);
        end -= numOfDays * TWENTY_FOUR_HOUR;

        System.out.println(new Date(start));

        List<Schedule> schedules = new ArrayList<>();
        for (int i = 0; i <= numOfDays; i++) {
            Schedule schedule = new Schedule();
            schedule.setStartTime(sf.format(start));

            System.out.println("date: " + sf.format(start));

            schedule.setEndTime(sf.format(end));
            System.out.println("date: " + schedule.getStartTime());
            schedules.add(schedule);
            start += TWENTY_FOUR_HOUR;
            end += TWENTY_FOUR_HOUR;
        }
        trainerService.addSchedule(trainerEmail, schedules);
        map.put("status", "OK");
        map.put("msg", "add schedule successful.");
        return map;
    }

    @RequestMapping(value = "/getSchedule", method = RequestMethod.GET)
    public List<Map<String, Object>> getReservation(HttpServletRequest request) throws ParseException {
        String trainerEmail = (String) request.getAttribute("userEmail");
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
            map.put("title", trainerReservation.getTitle());
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
    
    @RequestMapping(value = "/availableTime", method = RequestMethod.GET)
    public List<Map<String, Object>> getAvailableTime(HttpServletRequest request) throws ParseException {

        String trainerEmail = (String) request.getAttribute("userEmail");
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

}
