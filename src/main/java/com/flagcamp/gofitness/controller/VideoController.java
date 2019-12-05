package com.flagcamp.gofitness.controller;

import com.flagcamp.gofitness.dao.VideoDao;
import com.flagcamp.gofitness.model.Video;
import com.flagcamp.gofitness.repository.VideoRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class VideoController {

    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private VideoDao videoDao;

    @RequestMapping(value = "/getVideoId", method = RequestMethod.GET)
    public String getVideo(@RequestParam String traineeEmail, String trainerEmail, HttpServletRequest request) {
        Video video = videoRepository.findVideoByTraineeEmailAndAndTrainerEmail(traineeEmail, trainerEmail);
        if (video == null) {
            ObjectId id = new ObjectId();
            Video newVideo = new Video(id, traineeEmail, trainerEmail);
            videoDao.addVideo(newVideo);
            return id.toString();
        } else {
            return video.getId().toString();
        }
    }

}
