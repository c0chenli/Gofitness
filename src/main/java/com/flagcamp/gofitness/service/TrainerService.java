package com.flagcamp.gofitness.service;

import com.flagcamp.gofitness.model.Schedule;
import com.flagcamp.gofitness.model.Trainer;
import com.flagcamp.gofitness.model.TrainerReservation;

import java.util.List;

public interface TrainerService {

    /**
     *
     * @return
     */
    List<Trainer> getAllTrainers();

    /**
     *
     * @param trainerEmail
     * @return
     */
    List<Schedule> getSchedule(String trainerEmail, String now);

    /**
     *
     * @param trainerEmail
     * @param now
     * @return
     */
    List<TrainerReservation> getReservation(String trainerEmail, String now);

    /**
     *
     * @param trainerEmail
     * @param traineeEmail
     * @param traineeName
     * @param startTime
     * @param endTime
     */
    void addTrainerReservation(String trainerEmail, String traineeEmail, String traineeName, String startTime, String endTime);

    /**
     *
     * @param trainerEmail
     * @param schedules
     */
    void addSchedule(String trainerEmail, List<Schedule> schedules);

    /**
     *
     * @param trainerEmail
     * @return
     */
    String getFullName(String trainerEmail);

    /**
     *
     * @param email
     * @return
     */
    Trainer findTrainerByEmail(String email);

    /**
     *
     * @param email
     * @param password
     * @return
     */
    Trainer findTrainerByEmailAndPassword(String email, String password);

    /**
     *
     * @param trainer
     */
    void addNewTrainer(Trainer trainer);
    
}
