package com.flagcamp.gofitness.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class Video implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private ObjectId id;
    @Field("trainee_email")
    private String traineeEmail;
    @Field("trainer_email")
    private String trainerEmail;

    public Video() {

    }
    public Video(ObjectId id, String traineeEmail, String trainerEmail) {
        this.id = id;
        this.traineeEmail = traineeEmail;
        this.trainerEmail = trainerEmail;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getTraineeEmail() {
        return traineeEmail;
    }

    public void setTraineeEmail(String traineeEmail) {
        this.traineeEmail = traineeEmail;
    }

    public String getTrainerEmail() {
        return trainerEmail;
    }

    public void setTrainerEmail(String trainerEmail) {
        this.trainerEmail = trainerEmail;
    }
}
