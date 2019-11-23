package com.flagcamp.gofitness.controller;

import com.flagcamp.gofitness.model.Schedule;
import com.flagcamp.gofitness.model.Trainer;
import com.flagcamp.gofitness.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public Trainer getUserByEmail(HttpServletRequest request) {
        String trainerEmail = request.getAttribute("userEmail").toString();
        return trainerService.findTrainerByEmail(trainerEmail);
    }

    @PostMapping(value = "/setSchedule")
    public Map<String, String> setSchedule(@RequestBody Map<String, String> param, HttpServletRequest request) throws ParseException {
        Map<String, String> map = new HashMap<>();
        String trainerEmail = (String) request.getAttribute("userEmail");
        if (trainerEmail == null || trainerEmail.length() == 0) {
            map.put("status", "error");
            map.put("msg", "user login expired.");
            return map;
        }
        String startTime = param.get("start").replaceAll(",", "");
        String endTime = param.get("end").replaceAll(",", "");
        long start = sf.parse(startTime).getTime();
        long end = sf.parse(endTime).getTime();
        //24 hours
        long time = 60 * 24 * 60 * 1000;
        int numOfDays = (int) ((end - start) / time);
        end -= numOfDays * time;

        System.out.println(sf.format(end));

        List<Schedule> schedules = new ArrayList<>();
        for (int i = 0; i <= numOfDays; i++) {
            Schedule schedule = new Schedule();
            schedule.setStartTime(sf.format(start));
            schedule.setEndTime(sf.format(end));
            schedules.add(schedule);
            start += time;
            end += time;
        }
        trainerService.addSchedule(trainerEmail, schedules);
        map.put("status", "OK");
        map.put("msg", "add schedule successful.");
        return map;
    }

    @RequestMapping(value = "/getSchedule", method = RequestMethod.GET)
    public List<Schedule> getSchedule(HttpServletRequest request) {
        String trainerEmail = (String) request.getAttribute("userEmail");
        Date date = new Date();
        String now = sf.format(date);
        List<Schedule> schedules = trainerService.getSchedule(trainerEmail, now);
        return schedules;
    }

}
