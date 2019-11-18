package com.flagcamp.gofitness.service;

import com.flagcamp.gofitness.model.Trainer;

public interface TrainerService {
	
	void addTrainer(Trainer trainer);

    /**
     *
     * @param traineeEmail
     * @param startTime
     * @param endTime
     * @return
     */
    boolean addClass(String traineeEmail, String startTime, String endTime);
}
