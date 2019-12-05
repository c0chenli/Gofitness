package com.flagcamp.gofitness.repository;

import com.flagcamp.gofitness.model.Video;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VideoRepository extends MongoRepository<Video, String> {

    Video findVideoByTraineeEmailAndAndTrainerEmail(String traineeEmail, String trainerEmail);
}
