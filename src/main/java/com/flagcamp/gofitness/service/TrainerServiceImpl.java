package com.flagcamp.gofitness.service;

import com.flagcamp.gofitness.model.Trainer;
import com.flagcamp.gofitness.model.TrainerReservation;
import com.flagcamp.gofitness.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerServiceImpl implements TrainerService {

    @Autowired
    private TrainerRepository trainerRepository;

    /**
     * @return
     */
    @Override
    public List<Trainer> getAllTrainers() {
        return trainerRepository.findAll();
    }

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
     * @param email
     * @return
     */
    @Override
    public Trainer findTrainerByEmail(String email) {
       return trainerRepository.findTrainerByEmail(email);
    }

    /**
     * @param email
     * @param password
     * @return
     */
    @Override
    public Trainer findTrainerByEmailAndPassword(String email, String password) {
        return trainerRepository.findTrainerByEmailAndPassword(email, password);
    }

    /**
     * @param trainer
     */
    @Override
    public void addNewTrainer(Trainer trainer) {
        trainerRepository.save(trainer);
    }
}
