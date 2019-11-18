package com.flagcamp.gofitness.service;

import com.flagcamp.gofitness.model.Trainee;

public interface TraineeService {
	
	void addTrainee(Trainee trainee);
	
	void deleteTraineeByEmail(String email);

    /**
     *
     * @param traineeEmail
     * @return
     */
    String getFullName(String traineeEmail);

    /**
     *
     * @param email
     * @return
     */
    Trainee findTraineeByEmail(String email);

    /**
     *
     * @param email
     * @param password
     * @return
     */
    Trainee findTraineeByEmailAndPassword(String email, String password);

    /**
     *
     * @param trainee
     */
    void addNewTrainee(Trainee trainee);

}
