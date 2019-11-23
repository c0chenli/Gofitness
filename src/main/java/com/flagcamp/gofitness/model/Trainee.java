package com.flagcamp.gofitness.model;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Document
public class Trainee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Field(name = "first_name")
    private String firstname;
    @Field(name = "last_name")
    private String lastname;
    @Indexed(unique = true)
    @NotEmpty(message = "email cannot be empty!")
    private String email;
    @NotEmpty(message = "password cannot be empty!")
    private String password;
    private Set<TraineeReservation> traineeReservations;

    public Trainee() {
    	this.traineeReservations = new HashSet<>();
    }
    public Trainee(String firstname, String lastname, String email, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.traineeReservations = new HashSet<>();
    }

    public Set<TraineeReservation> getTraineeReservations() {
        return traineeReservations;
    }

    public void setTraineeReservations(Set<TraineeReservation> traineeReservations) {
        this.traineeReservations = traineeReservations;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
