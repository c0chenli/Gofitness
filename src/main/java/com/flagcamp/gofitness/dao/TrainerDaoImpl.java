package com.flagcamp.gofitness.dao;

import com.flagcamp.gofitness.dao.TrainerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.flagcamp.gofitness.model.Trainer;

@Component
public class TrainerDaoImpl implements TrainerDao {
	
	 @Autowired
	 MongoTemplate mongoTemplate;
	 
	 @Override
	 public void addTrainer(Trainer trainer) {
		 mongoTemplate.save(trainer);
	 }
	 

}