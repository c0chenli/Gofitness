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

        System.out.println(sf.format(end));

        List<Schedule> schedules = new ArrayList<>();
        for (int i = 0; i <= numOfDays; i++) {
            Schedule schedule = new Schedule();
            schedule.setStartTime(sf.format(start));
            schedule.setEndTime(sf.format(end));
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
    public List<Object> getSchedule(HttpServletRequest request) {
        String trainerEmail = (String) request.getAttribute("userEmail");
        Date date = new Date();
        String now = sf.format(date);
        List<Object> result = new ArrayList<>();
        result.addAll(trainerService.getSchedule(trainerEmail, now));
        result.addAll(trainerService.getReservation(trainerEmail, now));
        return result;
    }

}
