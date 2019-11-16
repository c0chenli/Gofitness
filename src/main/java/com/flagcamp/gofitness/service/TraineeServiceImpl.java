package com.flagcamp.gofitness.service;

import com.flagcamp.gofitness.model.Trainee;
import com.flagcamp.gofitness.repository.TraineeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class TraineeServiceImpl implements TraineeService {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private TraineeRepository traineeRepository;

    /**
     * @param trainee
     * @param trainerEmail
     * @param startTime
     * @param endTime      (discuss duration of each class)
     * @return true: reserve successful
     * false: reserve failed
     */
    @Override
    public boolean reserveClass(Trainee trainee, String trainerEmail, String startTime, String endTime) {
        return false;
    }

    /**
     * @param trainee
     * @param trainerEmail
     * @param startTime
     * @return
     */
    @Override
    public boolean cancelClass(Trainee trainee, String trainerEmail, String startTime) {
        return false;
    }
}
