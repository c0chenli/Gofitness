package com.flagcamp.gofitness.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.flagcamp.gofitness.model.Schedule;
import com.flagcamp.gofitness.model.Trainee;
import com.flagcamp.gofitness.model.TraineeReservation;

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

    /**
     *
     * @param traineeEmail
     * @param trainerEmail
     * @param trainerName
     * @param startTime
     * @param endTime
     */
    void addTraineeReservation(String traineeEmail, String trainerEmail, String trainerName, String startTime, String endTime);

    /**
     *
     * @param traineeEmail
     * @param now
     * @return
     * @throws ParseException
     */
    List<TraineeReservation> getTraineeReservation(String traineeEmail, String now) throws ParseException;

    /**
     *
     * @param traineeEmail
     * @param start
     * @throws ParseException 
     */
    void cancelReservation(String traineeEmail, long start) throws ParseException;

}
