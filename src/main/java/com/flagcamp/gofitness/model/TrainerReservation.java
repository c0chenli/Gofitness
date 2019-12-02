package com.flagcamp.gofitness.model;

import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class TrainerReservation implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;
    @Field("trainee_email")
    private String traineeEmail;
    @Field("trainee_name")
    private String traineeName;
    @DateTimeFormat
    @Field("start_time")
    private String startTime;
    @DateTimeFormat
    @Field("end_time")
    private String endTime;
    private int status;

    public TrainerReservation() {

    }

    public TrainerReservation(String title, String traineeEmail, String traineeName, String startTime, String endTime, int status) {
        this.title = title;
    	this.traineeEmail = traineeEmail;
        this.traineeName = traineeName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
