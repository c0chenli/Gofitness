package com.flagcamp.gofitness.dao;

import com.flagcamp.gofitness.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RoomDaoImpl implements RoomDao {

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public void addRoom(Room room) {
        mongoTemplate.save(room);
    }
}
