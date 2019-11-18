package com.flagcamp.gofitness.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.flagcamp.gofitness.model.Trainer;
import com.mongodb.client.result.UpdateResult;

@Component
public class TrainerDaoImpl implements TrainerDao {
	
	 @Autowired
	 MongoTemplate mongoTemplate;
	 
	 @Override
	 public void addTrainer(Trainer trainer) {
		 mongoTemplate.save(trainer);
	 }
	 

}