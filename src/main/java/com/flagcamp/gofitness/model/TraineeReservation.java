package com.flagcamp.gofitness.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection = "trainee")
public class TraineeReservation {

    @Field("trainer_email")
    private String traineeEmail;
    @DateTimeFormat
    @Field("start_time")
    private String startTime;
    @DateTimeFormat
    @Field("end_time")
    private String endTime;

    public TraineeReservation() {

    }

    public TraineeReservation(String traineeEmail, String startTime, String endTime) {
        this.traineeEmail = traineeEmail;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getTraineeEmail() {
        return traineeEmail;
    }

    public void setTraineeEmail(String traineeEmail) {
        this.traineeEmail = traineeEmail;
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
