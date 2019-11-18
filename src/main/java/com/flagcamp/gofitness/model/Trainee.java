package com.flagcamp.gofitness.model;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Component
public class Trainee {

    @Field(name = "first_name")
    private String firstname;
    @Field(name = "last_name")
    private String lastname;
    @Indexed(unique = true)
    @NotEmpty(message = "email cannot be empty!")
    private String email;
    @NotEmpty(message = "password cannot be empty!")
    private String password;
    private Set<String> categories;
    private Set<String> tags;
    private Set<TraineeReservation> traineeReservations;

    public Trainee() {
    	tags = new HashSet<>();
        traineeReservations = new HashSet<>();
    }
    public Trainee(String firstname, String lastname, String email, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        traineeReservations = new HashSet<>();
    }

    public void addTraineeReservation(TraineeReservation traineeReservation) {
        this.traineeReservations.add(traineeReservation);
    }

    public Set<TraineeReservation> getTraineeReservations() {
        return traineeReservations;
    }

    public void setTraineeReservations(Set<TraineeReservation> traineeReservations) {
        this.traineeReservations = traineeReservations;
    }

    public Set<String> getCategories() {
        return categories;
    }

    public void setCategories(Set<String> categories) {
        this.categories = categories;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
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
