package com.flagcamp.gofitness.dao;

import com.flagcamp.gofitness.model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.flagcamp.gofitness.model.Trainer;
import com.flagcamp.gofitness.model.TrainerReservation;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
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

	@Override
	public void addSchedule(String trainerEmail, List<Schedule> schedules) {
		Query query = new Query(Criteria.where("email").is(trainerEmail));
		Update update = new Update();
		for (Schedule schedule : schedules) {
			update.addToSet("schedules", schedule);
			mongoTemplate.upsert(query, update, Trainer.class);
		}
	}
	
	@Override
	public void addTrainerReservation(String trainerEmail, TrainerReservation trainerReservation) {
		Query query = new Query(Criteria.where("email").is(trainerEmail));
		Update update = new Update();
		update.addToSet("trainerReservations", trainerReservation);
		mongoTemplate.upsert(query, update, Trainer.class);
	}


}