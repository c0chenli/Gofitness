package com.flagcamp.gofitness.model;

import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

public class TraineeReservation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Field("trainer_email")
    private String trainerEmail;
    @Field("trainer_name")
    private String trainerName;
    @DateTimeFormat
    @Field("start_time")
    private String startTime;
    @DateTimeFormat
    @Field("end_time")
    private String endTime;
    private String status;

    public TraineeReservation() {

    }

    public TraineeReservation(String trainerEmail, String trainerName, String startTime, String endTime, String status) {
        this.trainerEmail = trainerEmail;
        this.trainerName = trainerName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
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
