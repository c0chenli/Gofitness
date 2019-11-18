package com.flagcamp.gofitness.dao;


import com.flagcamp.gofitness.model.*;

public interface TrainerDao {
	
	void addTrainer(Trainer trainer);

	void deleteTrainerByEmail(String email);
	
}
