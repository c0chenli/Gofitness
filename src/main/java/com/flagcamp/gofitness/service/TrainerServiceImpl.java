package com.flagcamp.gofitness.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.flagcamp.gofitness.model.Trainer;
import com.flagcamp.gofitness.repository.TrainerDao;
import com.flagcamp.gofitness.repository.TrainerRepository;

@Service
public class TrainerServiceImpl implements TrainerService {
	
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private TrainerRepository trainerRepository;
    @Autowired
    private TrainerDao trainerDao;

    public void addTrainer(Trainer trainer) {   	 
    	trainerDao.addTrainer(trainer);
    }
	
	
	
    /**
     * @param traineeEmail
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public boolean addClass(String traineeEmail, String startTime, String endTime) {
        return false;
    }
}
