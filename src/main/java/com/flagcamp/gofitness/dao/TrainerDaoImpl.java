package com.flagcamp.gofitness.dao;

import com.flagcamp.gofitness.model.Schedule;
import com.flagcamp.gofitness.model.Trainee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.flagcamp.gofitness.model.Trainer;
import com.flagcamp.gofitness.model.TrainerReservation;

import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.List;

@Repository
public class TrainerDaoImpl implements TrainerDao {
	
	 @Autowired
	 MongoTemplate mongoTemplate;
	 
	 private SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmm");
	 
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
	
    @Override
    public void cancelReservation(String trainerEmail, long start) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(trainerEmail).and("trainerReservations").elemMatch(Criteria.where("startTime").is(sf.format(start))));
        Update update = new Update();
        update.pull("trainerReservations", new Query().addCriteria(Criteria.where("startTime").is(sf.format(start))));
        mongoTemplate.upsert(query, update, Trainer.class);
    }


}