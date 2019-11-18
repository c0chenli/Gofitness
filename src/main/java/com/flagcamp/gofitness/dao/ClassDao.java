package com.flagcamp.gofitness.dao;

import com.flagcamp.gofitness.model.TraineeReservation;
import com.flagcamp.gofitness.model.TrainerReservation;

public interface ClassDao {

    void reserveClass(String traineeEmail, String trainerEmail, TraineeReservation traineeReservation, TrainerReservation trainerReservation);

    void cancelClass(String traineeEmail, String trainerEmail, TraineeReservation traineeReservation, TrainerReservation trainerReservation);
}
