package com.flagcamp.gofitness.service;

import com.flagcamp.gofitness.dao.TraineeDao;
import com.flagcamp.gofitness.model.Trainee;
import com.flagcamp.gofitness.repository.TraineeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TraineeServiceImpl implements TraineeService {

    @Autowired
    private TraineeRepository traineeRepository;
    @Autowired
    private TraineeDao traineeDao;

    public void addTrainee(Trainee trainee) {   	 
    	traineeDao.addTrainee(trainee);
    }

    public void deleteTraineeByEmail(String email) {
    	traineeDao.deleteTraineeByEmail(email);
    }

    /**
     * @param traineeEmail
     * @return
     */
    @Override
    public String getFullName(String traineeEmail) {
        Trainee trainee = traineeRepository.findTraineeByEmail(traineeEmail);
        if (trainee == null) {
            return null;
        }
        String fullName = trainee.getFirstname() + " " + trainee.getLastname();
        return fullName;
    }

    /**
     * @param email
     * @return
     */
    @Override
    public Trainee findTraineeByEmail(String email) {
        return traineeRepository.findTraineeByEmail(email);
    }

    /**
     * @param email
     * @param password
     * @return
     */
    @Override
    public Trainee findTraineeByEmailAndPassword(String email, String password) {
        return traineeRepository.findTraineeByEmailAndPassword(email, password);
    }

    /**
     * @param trainee
     */
    @Override
    public void addNewTrainee(Trainee trainee) {
        traineeRepository.save(trainee);
    }

}
