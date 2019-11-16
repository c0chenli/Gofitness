package com.flagcamp.gofitness.repository;

import com.flagcamp.gofitness.model.Trainee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TraineeRepository extends MongoRepository<Trainee, String> {

    Trainee findTraineeByEmail(String email);

    Trainee findTraineeByEmailAndPassword(String email, String password);

}
