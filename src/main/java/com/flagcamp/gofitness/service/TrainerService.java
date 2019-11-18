package com.flagcamp.gofitness.service;

public interface TrainerService {

    /**
     *
     * @param trainerEmail
     * @return
     */
    String getFullName(String trainerEmail);

    /**
     *
     * @param traineeEmail
     * @param startTime
     * @param endTime
     */
    void addClass(String traineeEmail, String startTime, String endTime);
}
