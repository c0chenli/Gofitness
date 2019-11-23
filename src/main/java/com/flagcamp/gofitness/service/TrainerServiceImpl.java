package com.flagcamp.gofitness.service;

import com.flagcamp.gofitness.dao.TrainerDao;
import com.flagcamp.gofitness.model.Schedule;
import com.flagcamp.gofitness.model.Trainer;
import com.flagcamp.gofitness.model.TrainerReservation;
import com.flagcamp.gofitness.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TrainerServiceImpl implements TrainerService {

    @Autowired
    private TrainerRepository trainerRepository;
    @Autowired
    private TrainerDao trainerDao;

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
    public List<Schedule> getSchedule(String trainerEmail, Date now) {
        Trainer trainer = trainerRepository.findTrainerByEmail(trainerEmail);
        List<Schedule> schedules = new ArrayList<>();
        schedules.addAll(trainer.getSchedules());
        Collections.sort(schedules, Comparator.comparing(Schedule::getStartTime));
        for (Schedule schedule : schedules) {
            if (schedule.getStartTime().compareTo(now) < 0) {
                schedules.remove(schedule);
            }
        }
        return schedules;
    }

    /**
     * @param trainerEmail
     * @param now
     * @return
     */
    @Override
    public List<TrainerReservation> getReservation(String trainerEmail, Date now) {
        Trainer trainer = trainerRepository.findTrainerByEmail(trainerEmail);
        List<TrainerReservation> reservations = new ArrayList<>();
        reservations.addAll(trainer.getTrainerReservations());
        Collections.sort(reservations, Comparator.comparing(TrainerReservation::getStartTime));
        for (TrainerReservation reservation : reservations) {
            if (reservation.getStartTime().compareTo(now) < 0) {
                reservations.remove(reservation);
            }
        }
        return reservations;
    }

    /**
     * @param trainerEmail
     * @param traineeEmail
     * @param traineeName
     * @param startTime
     * @param endTime
     */
    @Override
    public void addTrainerReservation(String trainerEmail, String traineeEmail, String traineeName, Date startTime, Date endTime) {
        TrainerReservation trainerReservation = new TrainerReservation();
        trainerReservation.setTraineeEmail(traineeEmail);
        trainerReservation.setTraineeName(traineeName);
        trainerReservation.setStartTime(startTime);
        trainerReservation.setEndTime(endTime);
        trainerReservation.setStatus(0);
        trainerDao.addTrainerReservation(trainerEmail, trainerReservation);
    }

    /**
     * @param trainerEmail
     * @param schedules
     */
    @Override
    public void addSchedule(String trainerEmail, List<Schedule> schedules) {
        trainerDao.addSchedule(trainerEmail, schedules);
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
