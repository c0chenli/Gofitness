package com.flagcamp.gofitness.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.flagcamp.gofitness.model.Trainee;
import org.springframework.stereotype.Repository;

@Repository
public class TraineeDaoImpl implements TraineeDao {
	
	 @Autowired
	 MongoTemplate mongoTemplate;
	 
	 @Override
	 public void addTrainee(Trainee trainee) {
		 mongoTemplate.save(trainee);
	 }
	 
	 @Override
	 public void deleteTraineeByEmail(String email) {
		 Query query=new Query(Criteria.where("email").is(email));
		 mongoTemplate.remove(query,Trainee.class);
	 }

}
