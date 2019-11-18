package com.flagcamp.gofitness.service;

import com.flagcamp.gofitness.model.Trainee;
import com.flagcamp.gofitness.model.TraineeReservation;
import com.flagcamp.gofitness.repository.TraineeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TraineeServiceImpl implements TraineeService {

    @Autowired
    private TraineeDao traineeDao;
    @Autowired
    private TraineeRepository traineeRepository;

    /**
     * @param traineeEmail
     * @return
     */
    @Override
    public String getFullName(String traineeEmail) {
        Trainee trainee = traineeRepository.findTraineeByEmail(traineeEmail);
        if (trainee == null) {
            return null;
        }
        String fullName = trainee.getFirstname() + " " + trainee.getLastname();
        return fullName;
    }

    /**
     *
     * @param traineeEmail
     * @param trainerEmail
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public void reserveClass(String traineeEmail, String trainerEmail, String startTime, String endTime) {
        TraineeReservation traineeReservation = new TraineeReservation(trainerEmail, startTime, endTime);
        traineeDao.addClassToTrainee(traineeEmail, traineeReservation);
    }

    /**
     *
     * @param trainee
     * @param trainerEmail
     * @param startTime
     */
    @Override
    public void cancelClass(Trainee trainee, String trainerEmail, String startTime) {

    }
}
