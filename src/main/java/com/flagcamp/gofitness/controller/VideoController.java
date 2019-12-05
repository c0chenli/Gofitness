package com.flagcamp.gofitness.controller;

import com.flagcamp.gofitness.dao.RoomDao;
import com.flagcamp.gofitness.model.Room;
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
    private RoomDao roomDao;

    @RequestMapping(value = "/getRoomId", method = RequestMethod.GET)
    public Map<String, String> getVideo(@RequestParam String traineeEmail, String trainerEmail, HttpServletRequest request) {
        Room room = videoRepository.findRoomByTraineeEmailAndAndTrainerEmail(traineeEmail, trainerEmail);
        Map<String, String> map = new HashMap<>();
        ObjectId id;
        if (room == null) {
            id = new ObjectId();
            Room newRoom = new Room(id, traineeEmail, trainerEmail);
            roomDao.addRoom(newRoom);
        } else {
            id = room.getId();
        }
        map.put("status", "OK");
        map.put("room_id", id.toString());
        return map;
    }

}
