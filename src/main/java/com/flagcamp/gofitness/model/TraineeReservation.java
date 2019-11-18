package com.flagcamp.gofitness.model;

import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

public class TraineeReservation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Field("trainer_email")
    private String trainerEmail;
    @DateTimeFormat
    @Field("start_time")
    private String startTime;
    @DateTimeFormat
    @Field("end_time")
    private String endTime;

    public TraineeReservation() {

    }

    public TraineeReservation(String trainerEmail, String startTime, String endTime) {
        this.trainerEmail = trainerEmail;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getTrainerEmail() {
        return trainerEmail;
    }

    public void setTrainerEmail(String trainerEmail) {
        this.trainerEmail = trainerEmail;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
