package com.flagcamp.gofitness.dao;


import com.flagcamp.gofitness.model.*;

import java.util.List;

public interface TrainerDao {
	
	void addTrainer(Trainer trainer);

	void deleteTrainerByEmail(String email);

	void addSchedule(String trainerEmail, List<Schedule> schedules);
	
	void addTrainerReservation(String trainerEmail, TrainerReservation trainerReservation);
}
