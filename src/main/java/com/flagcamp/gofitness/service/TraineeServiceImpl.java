package com.flagcamp.gofitness.service;

import com.flagcamp.gofitness.dao.TraineeDao;
import com.flagcamp.gofitness.dao.TrainerDao;
import com.flagcamp.gofitness.model.Schedule;
import com.flagcamp.gofitness.model.Trainee;
import com.flagcamp.gofitness.model.TraineeReservation;
import com.flagcamp.gofitness.repository.TraineeRepository;
import com.flagcamp.gofitness.repository.TrainerRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TraineeServiceImpl implements TraineeService {

    @Autowired
    private TraineeRepository traineeRepository;
    @Autowired
    private TraineeDao traineeDao;
    
    private SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmm");

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

    /**
     * @param traineeEmail
     * @param trainerEmail
     * @param trainerName
     * @param startTime
     * @param endTime
     */
    @Override
    public void addTraineeReservation(String traineeEmail, String trainerEmail, String trainerName, String startTime, String endTime) {
        System.out.println(traineeEmail + " " + trainerEmail + " " + trainerName);
        TraineeReservation traineeReservation = new TraineeReservation();
        traineeReservation.setTrainerEmail(trainerEmail);
        traineeReservation.setTrainerName(trainerName);
        traineeReservation.setStartTime(startTime);
        traineeReservation.setEndTime(endTime);
        traineeReservation.setStatus(0);
        traineeDao.addTraineeReservation(traineeEmail, traineeReservation);
    }


    @Override
    public List<TraineeReservation> getTraineeReservation(String traineeEmail, String now) throws ParseException {
    	Trainee trainee = traineeRepository.findTraineeByEmail(traineeEmail);
        System.out.println(traineeEmail);
        System.out.println(now);
    	Set<TraineeReservation> set = trainee.getTraineeReservations();
    	if (set == null || set.size() == 0) {
    		System.out.println("Oops, Set<TraineeReservation> is null");
    	}
    	System.out.println("set is fine");
    	Iterator<TraineeReservation> iter = set.iterator();
        long curTime = sf.parse(now).getTime();
        while (iter.hasNext()) {
        	TraineeReservation cur = iter.next();
        	String time = cur.getStartTime();
        	long start = sf.parse(time).getTime();
        	if (start < curTime) {
        		iter.remove();
        	}
        }
        List<TraineeReservation> result = new ArrayList<>(set);
        if (result == null || result.size() == 0) {
        	System.out.println("Oops, list<TraineeReservation> is null");
        }
        System.out.println("list is fine");
        return result;
//    	List<TraineeReservation> list = new ArrayList<>();
//    	for (TraineeReservation traineeReservation: trainee.getTraineeReservations()) {
//    		if (traineeReservation.getStartTime().compareTo(now) >= 0) {
//    			list.add(traineeReservation);
//    		}
//    	}
//    	return list;
    	
    }
    
    @Override
    public void cancelReservation(String traineeEmail, long start) {
    	traineeDao.cancelReservation(traineeEmail, start);
    }
    
    

}
