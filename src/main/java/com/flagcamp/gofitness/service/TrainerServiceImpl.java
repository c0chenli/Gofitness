package com.flagcamp.gofitness.service;

import com.flagcamp.gofitness.model.Trainer;
import com.flagcamp.gofitness.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainerServiceImpl implements TrainerService {

    @Autowired
    private TrainerRepository trainerRepository;

    /**
     * @param trainerEmail
     * @return
     */
    @Override
    public String getFullName(String trainerEmail) {
        Trainer trainer = trainerRepository.findTrainerByEmail(trainerEmail);
        if (trainer == null) {
            return null;
        }
        String fullName = trainer.getFirstname() + " " + trainer.getLastname();
        return fullName;
    }

    /**
     * @param traineeEmail
     * @param startTime
     * @param endTime
     */
    @Override
    public void addClass(String traineeEmail, String startTime, String endTime) {

    }
}
