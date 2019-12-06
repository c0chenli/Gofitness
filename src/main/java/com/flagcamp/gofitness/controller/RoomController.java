package com.flagcamp.gofitness.controller;

import com.flagcamp.gofitness.dao.RoomDao;
import com.flagcamp.gofitness.model.Room;
import com.flagcamp.gofitness.repository.RoomRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomDao roomDao;

    @RequestMapping(value = "/getRoomId", method = RequestMethod.GET)
    public Map<String, String> getVideo(@RequestParam(value = "email_1") String emailOne, @RequestParam(value = "email_2") String emailTwo, HttpServletRequest request) {
        Room room = roomRepository.findRoomByEmailOneOrEmailTwo(emailOne, emailTwo);
        Map<String, String> map = new HashMap<>();
        ObjectId id;
        if (room == null) {
            id = new ObjectId();
            Room newRoom = new Room(id, emailOne, emailTwo);
            roomDao.addRoom(newRoom);
        } else {
            id = room.getId();
        }
        map.put("status", "OK");
        map.put("room_id", id.toString());
        return map;
    }

}
