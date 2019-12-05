package com.flagcamp.gofitness.controller;

import com.flagcamp.gofitness.dao.VideoDao;
import com.flagcamp.gofitness.model.Video;
import com.flagcamp.gofitness.repository.VideoRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class VideoController {

    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private VideoDao videoDao;

    @RequestMapping(value = "/getRoomId", method = RequestMethod.GET)
    public Map<String, String> getVideo(@RequestParam String traineeEmail, String trainerEmail, HttpServletRequest request) {
        Video video = videoRepository.findVideoByTraineeEmailAndAndTrainerEmail(traineeEmail, trainerEmail);
        Map<String, String> map = new HashMap<>();
        ObjectId id;
        if (video == null) {
            id = new ObjectId();
            Video newVideo = new Video(id, traineeEmail, trainerEmail);
            videoDao.addVideo(newVideo);
        } else {
            id = video.getId();
        }
        map.put("status", "OK");
        map.put("room_id", id.toString());
        return map;
    }

}
