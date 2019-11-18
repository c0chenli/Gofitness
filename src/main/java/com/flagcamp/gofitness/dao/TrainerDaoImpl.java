package com.flagcamp.gofitness.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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

	@Override
	public void deleteTrainerByEmail(String email) {
		Query query=new Query(Criteria.where("email").is(email));
		mongoTemplate.remove(query, Trainer.class);
	}


}