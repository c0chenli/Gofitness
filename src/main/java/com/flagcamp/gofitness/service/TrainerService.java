package com.flagcamp.gofitness.service;

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
