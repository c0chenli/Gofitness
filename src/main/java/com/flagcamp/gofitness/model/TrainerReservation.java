package com.flagcamp.gofitness.model;

import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

public class TrainerReservation {

    @Field("trainee_id")
    private String traineeId;
    @DateTimeFormat
    @Field("start_time")
    private String startTime;
    @DateTimeFormat
    @Field("end_time")
    private String endTime;

    public TrainerReservation() {

    }
    public TrainerReservation(String traineeId, String startTime, String endTime) {
        this.traineeId = traineeId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getTraineeId() {
        return traineeId;
    }

    public void setTraineeId(String traineeId) {
        this.traineeId = traineeId;
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

