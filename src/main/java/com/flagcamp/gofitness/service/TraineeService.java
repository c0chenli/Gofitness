package com.flagcamp.gofitness.service;

import com.flagcamp.gofitness.model.Trainee;

public interface TraineeService {

    /**
     *
     * @param traineeEmail
     * @param trainerEmail
     * @param startTime
     * @param endTime
     */
    void reserveClass(String traineeEmail, String trainerEmail, String startTime, String endTime);

    /**
     *
     * @param traineeEmail
     * @return
     */
    String getFullName(String traineeEmail);

    /**
     *
     * @param trainee
     * @param trainerEmail
     * @param startTime
     */
    void cancelClass(Trainee trainee, String trainerEmail, String startTime);
}
