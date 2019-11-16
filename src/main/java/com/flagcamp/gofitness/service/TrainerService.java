package com.flagcamp.gofitness.service;

public interface TrainerService {

    /**
     *
     * @param traineeEmail
     * @param startTime
     * @param endTime
     * @return
     */
    boolean addClass(String traineeEmail, String startTime, String endTime);
}
