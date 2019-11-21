package com.flagcamp.gofitness.service;

import com.flagcamp.gofitness.model.Schedule;
import com.flagcamp.gofitness.model.Trainer;
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
