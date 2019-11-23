package com.flagcamp.gofitness.model;

import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class TrainerReservation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Field("trainee_email")
    private String traineeEmail;
    @Field("trainee_name")
    private String traineeName;
    @DateTimeFormat
    @Field("start_time")
    private Date startTime;
    @DateTimeFormat
    @Field("end_time")
    private Date endTime;
    private int status;

    public TrainerReservation() {

    }

    public TrainerReservation(String traineeEmail, String traineeName, Date startTime, Date endTime, int status) {
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
