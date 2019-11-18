package com.flagcamp.gofitness.repository;

import com.flagcamp.gofitness.model.*;

public interface TraineeDao {
	
	void addTrainee(Trainee trainee);
	
	void deleteTraineeByEmail(String email);
	
}
