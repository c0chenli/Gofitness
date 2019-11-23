package com.flagcamp.gofitness.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class TraineeReservation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Field("trainer_email")
    private String trainerEmail;
    @Field("trainer_name")
    private String trainerName;
    @DateTimeFormat
    @Field("start_time")
    private Date startTime;
    @DateTimeFormat
    @Field("end_time")
    private Date endTime;
    private int status;

    public TraineeReservation() {

    }

    public TraineeReservation(String trainerEmail, String trainerName, Date startTime, Date endTime, int status) {
        this.trainerEmail = trainerEmail;
        this.trainerName = trainerName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
