package com.flagcamp.gofitness.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.flagcamp.gofitness.model.Schedule;
import com.flagcamp.gofitness.model.Trainee;
import com.flagcamp.gofitness.model.TraineeReservation;
import com.flagcamp.gofitness.model.Trainer;

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
	 
	@Override
	public void addTraineeReservation(String traineeEmail, List<TraineeReservation> reservations) {
		Query query = new Query(Criteria.where("email").is(traineeEmail));
		Update update = new Update();
		for (TraineeReservation reservation : reservations) {
			update.addToSet("traineeReservations", reservation);
			mongoTemplate.upsert(query, update, Trainee.class);
		}
	}

}
