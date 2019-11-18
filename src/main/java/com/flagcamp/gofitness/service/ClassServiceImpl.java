package com.flagcamp.gofitness.service;

import com.flagcamp.gofitness.dao.ClassDao;
import com.flagcamp.gofitness.model.TraineeReservation;
import com.flagcamp.gofitness.model.TrainerReservation;
import com.flagcamp.gofitness.repository.TraineeRepository;
import com.flagcamp.gofitness.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassDao classDao;
    @Autowired
    private TrainerRepository trainerRepository;
    @Autowired
    private TraineeRepository traineeRepository;

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
        Set<TraineeReservation> traineeReservations = traineeRepository.findTraineeByEmail(traineeEmail).getTraineeReservations();
        Set<TrainerReservation> trainerReservations = trainerRepository.findTrainerByEmail(trainerEmail).getTrainerReservations();
        TraineeReservation traineeReservation = null;
        TrainerReservation trainerReservation = null;
        for (TraineeReservation cur : traineeReservations) {
            if (cur.getTrainerEmail().equals(trainerEmail) && cur.getStartTime().equals(startTime)) {
                traineeReservation = cur;
            }
        }
        for (TrainerReservation cur : trainerReservations) {
            if (cur.getTraineeEmail().equals(traineeEmail) && cur.getStartTime().equals(startTime)) {
                trainerReservation = cur;
            }
        }
        System.out.println(traineeReservation +" "+ trainerReservation);
        if (traineeReservation != null && trainerReservation != null) {
            classDao.cancelClass(traineeEmail, trainerEmail, traineeReservation,trainerReservation);
        }
    }
}
