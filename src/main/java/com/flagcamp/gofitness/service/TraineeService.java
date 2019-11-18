package com.flagcamp.gofitness.service;

import com.flagcamp.gofitness.model.Trainee;

public interface TraineeService {
	
	void addTrainee(Trainee trainee);
	
	void deleteTraineeByEmail(String email);

    /**
     * @param trainee
     * @param trainerEmail
     * @param startTime
     * @param endTime (discuss duration of each class)
     * @return true: reserve successful
     *         false: reserve failed
     */
    boolean reserveClass(Trainee trainee, String trainerEmail, String startTime, String endTime);
    /**
     * @param trainee
     * @param trainerEmail
     * @param startTime
     * @return
     */
    boolean cancelClass(Trainee trainee, String trainerEmail, String startTime);
}
