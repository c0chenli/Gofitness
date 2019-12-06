package com.flagcamp.gofitness.repository;

import com.flagcamp.gofitness.model.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoomRepository extends MongoRepository<Room, String> {

    Room findRoomByEmailOneOrEmailTwo(String emailOne, String emailTwo);
}
