package com.flagcamp.gofitness.service;

import org.springframework.stereotype.Service;

@Service
public class TrainerServiceImpl implements TrainerService {
    /**
     * @param traineeEmail
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public boolean addClass(String traineeEmail, String startTime, String endTime) {
        return false;
    }
}
