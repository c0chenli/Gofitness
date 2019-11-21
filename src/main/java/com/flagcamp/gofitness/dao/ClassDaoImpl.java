package com.flagcamp.gofitness.dao;

import com.flagcamp.gofitness.model.Trainee;
import com.flagcamp.gofitness.model.TraineeReservation;
import com.flagcamp.gofitness.model.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class ClassDaoImpl implements ClassDao {

    @Autowired
    private MongoTemplate mongoTemplate;

//    @Override
//    public void reserveClass(String traineeEmail, String trainerEmail, TraineeReservation traineeReservation, TrainerReservation trainerReservation) {
//        //step 1. Add class reservation to trainee
//        Query query = new Query(Criteria.where("email").is(traineeEmail));
//        Update update = new Update().addToSet("traineeReservations", traineeReservation);
//        mongoTemplate.upsert(query, update, Trainee.class);
//
//        //step 2. Add class reservation to trainer
//        query = new Query(Criteria.where("email").is(trainerEmail));
//        update = new Update().addToSet("trainerReservations", trainerReservation);
//        mongoTemplate.upsert(query, update, Trainer.class);
//    }
//
//    @Override
//    public void cancelClass(String traineeEmail, String trainerEmail, TraineeReservation traineeReservation, TrainerReservation trainerReservation) {
//        //step 1. remove class from trainee
//        Query query = new Query(Criteria.where("email").is(traineeEmail));
//        Update update = new Update().pull("traineeReservations", traineeReservation);
//        mongoTemplate.upsert(query, update, Trainee.class);
//        //step 2. remove class from trainer
//        query = new Query(Criteria.where("email").is(trainerEmail));
//        update = new Update().pull("trainerReservations", trainerReservation);
//        mongoTemplate.upsert(query, update, Trainer.class);
//    }
}
