package com.flagcamp.gofitness.service;

import com.flagcamp.gofitness.dao.TraineeDao;
import com.flagcamp.gofitness.dao.TrainerDao;
import com.flagcamp.gofitness.model.*;
import com.flagcamp.gofitness.repository.TraineeRepository;
import com.flagcamp.gofitness.repository.TrainerRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TraineeServiceImpl implements TraineeService {

    @Autowired
    private TraineeRepository traineeRepository;
    @Autowired
    private TrainerRepository trainerRepository;
    @Autowired
    private TraineeDao traineeDao;
    @Autowired
    private TrainerDao trainerDao;
    
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
    public void addTraineeReservation(String title, String traineeEmail, String trainerEmail, String trainerName, String startTime, String endTime) {
        System.out.println(traineeEmail + " " + trainerEmail + " " + trainerName);
        TraineeReservation traineeReservation = new TraineeReservation();
        traineeReservation.setTitle(title);
        traineeReservation.setTrainerEmail(trainerEmail);
        traineeReservation.setTrainerName(trainerName);
        traineeReservation.setStartTime(startTime);
        traineeReservation.setEndTime(endTime);
        traineeReservation.setStatus(0);
        traineeDao.addTraineeReservation(traineeEmail, traineeReservation);
    }

    /**
     * @param trainerEmail
     * @param startTime
     * @param endTime
     */
    @Override
    public boolean checkTrainerTime(String trainerEmail, String startTime, String endTime) {
        Trainer trainer = trainerRepository.findTrainerByEmail(trainerEmail);
        Set<Schedule> schedules = trainer.getSchedules();
        Set<TrainerReservation> trainerReservations = trainer.getTrainerReservations();
        if (checkSchedule(schedules, startTime, endTime) && checkReservation(trainerReservations, startTime, endTime)) {
            return true;
        }
        return false;
    }
    private boolean checkSchedule(Set<Schedule> schedules, String startTime, String endTime) {
        for (Schedule schedule : schedules) {
            if (schedule.getStartTime().compareTo(startTime) <= 0 && schedule.getEndTime().compareTo(endTime) >= 0) {
                return true;
            }
        }
        return false;
    }
    private boolean checkReservation(Set<TrainerReservation> trainerReservations, String startTime, String endTime) {
        for (TrainerReservation trainerReservation : trainerReservations) {
            if (startTime.compareTo(trainerReservation.getEndTime()) >= 0 || endTime.compareTo(trainerReservation.getStartTime()) <= 0) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * @param traineeEmail
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public boolean checkTraineeTime(String traineeEmail, String startTime, String endTime) {
        Trainee trainee = traineeRepository.findTraineeByEmail(traineeEmail);
        Set<TraineeReservation> traineeReservations = trainee.getTraineeReservations();
        for (TraineeReservation traineeReservation : traineeReservations) {
            if (startTime.compareTo(traineeReservation.getEndTime()) >= 0 || endTime.compareTo(traineeReservation.getStartTime()) <= 0) {
                continue;
            } else {
                return false;
            }
        }
        return true;
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
        Collections.sort(result, Comparator.comparing(TraineeReservation::getStartTime));
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
    public void cancelReservation(String traineeEmail, long start) throws ParseException {
    	//如何能直接保证同时删除：利用traineeEmail和start得到traineeReservation的信息中的教练邮箱
    	Trainee trainee = traineeRepository.findTraineeByEmail(traineeEmail);
    	Set<TraineeReservation> set = trainee.getTraineeReservations();
    	String trainerEmail = "";
    	Iterator<TraineeReservation> iter = set.iterator();
    	//TODO 这里没拿到email
    	while (iter.hasNext()) {
    		TraineeReservation cur = iter.next();
    		if (start == sf.parse(cur.getStartTime()).getTime()) {
    			trainerEmail = cur.getTrainerEmail();
    		}
    	}
    	System.out.println("trainerEmail for cancel");
    	System.out.println(trainerEmail);
    	traineeDao.cancelReservation(traineeEmail, start);
    	trainerDao.cancelReservation(trainerEmail, start);
    }
    
    

}
