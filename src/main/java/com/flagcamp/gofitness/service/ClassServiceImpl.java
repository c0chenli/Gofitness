package com.flagcamp.gofitness.service;

import com.flagcamp.gofitness.dao.ClassDao;
import com.flagcamp.gofitness.model.TraineeReservation;
import com.flagcamp.gofitness.model.TrainerReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassDao classDao;

    /**
     * @param traineeEmail
     * @param trainerEmail
     * @param startTime
     * @param endTime
     */
    @Override
    public void reserveClass(String traineeEmail, String trainerEmail, String startTime, String endTime) {
        TraineeReservation traineeReservation = new TraineeReservation(trainerEmail, startTime, endTime);
        TrainerReservation trainerReservation = new TrainerReservation(traineeEmail, startTime, endTime);
        classDao.reserveClass(traineeEmail, trainerEmail, traineeReservation, trainerReservation);
    }

    /**
     * @param traineeEmail
     * @param trainerEmail
     * @param startTime
     * @param endTime
     */
    @Override
    public void cancelClass(String traineeEmail, String trainerEmail, String startTime, String endTime) {

    }
}
