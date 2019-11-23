package com.flagcamp.gofitness.dao;

import java.util.List;

import com.flagcamp.gofitness.model.*;

public interface TraineeDao {
	
	void addTrainee(Trainee trainee);
	
	void deleteTraineeByEmail(String email);
	
	void addTraineeReservation(String traineeEmail, List<TraineeReservation> reservations);
	
	void cancelReservation(String traineeEmail, long start);
	
}
