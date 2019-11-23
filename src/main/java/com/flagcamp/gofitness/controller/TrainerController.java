package com.flagcamp.gofitness.controller;

import com.flagcamp.gofitness.model.Schedule;
import com.flagcamp.gofitness.model.Trainer;
import com.flagcamp.gofitness.model.TrainerReservation;
import com.flagcamp.gofitness.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public Trainer getUserByEmail(HttpServletRequest request) {
        String trainerEmail = (String) request.getAttribute("userEmail");
        return trainerService.findTrainerByEmail(trainerEmail);
    }

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
    
    @RequestMapping(value = "/availableTime", method = RequestMethod.GET)
    public List<Object> getAvailableTime(HttpServletRequest request) {
        String trainerEmail = (String) request.getAttribute("userEmail");
        Date date = new Date();
        String now = sf.format(date);
        List<Object> result = new ArrayList<>();
        result.addAll(trainerService.getSchedule(trainerEmail, now));
        return result;
    }

}
