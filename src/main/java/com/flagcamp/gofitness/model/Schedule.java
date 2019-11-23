package com.flagcamp.gofitness.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

public class Schedule implements Serializable {

    private static final long serialVersionUID = 1L;

    @Field("trainee_name")
    private String traineeName;
    @Field("trainee_email")
    private String traineeEmail;
    @DateTimeFormat
    @Field("start_time")
    private String startTime;
    @DateTimeFormat
    @Field("end_time")
    private String endTime;
    private String status;

    public Schedule() {

    }
    public Schedule(String traineeEmail, String traineeName, String startTime, String endTime, String status) {
        this.traineeEmail = traineeEmail;
        this.traineeName = traineeName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    public String getTraineeEmail() {
        return traineeEmail;
    }

    public void setTraineeEmail(String traineeEmail) {
        this.traineeEmail = traineeEmail;
    }

    public String getTraineeName() {
        return traineeName;
    }

    public void setTraineeName(String traineeName) {
        this.traineeName = traineeName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

