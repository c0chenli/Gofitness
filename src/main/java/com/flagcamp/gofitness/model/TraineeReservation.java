package com.flagcamp.gofitness.model;

import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

public class TraineeReservation {

    @Field("trainer_email")
    private String traineeEmail;
    @DateTimeFormat
    @Field("start_time")
    private String startTime;
    @DateTimeFormat
    @Field("end_time")
    private String endTime;

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
