package com.flagcamp.gofitness.repository;

import com.flagcamp.gofitness.model.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VideoRepository extends MongoRepository<Room, String> {

    Room findRoomByTraineeEmailAndAndTrainerEmail(String traineeEmail, String trainerEmail);
}
