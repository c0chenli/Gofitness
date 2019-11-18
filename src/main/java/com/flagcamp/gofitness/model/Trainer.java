package com.flagcamp.gofitness.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Document
public class Trainer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Field("first_name")
    private String firstname;
    @Field("last_name")
    private String lastname;
    @Indexed(unique = true)
    @NotEmpty(message = "email cannot be empty!")
    private String email;
    @NotEmpty(message = "password cannot be empty!")
    private String password;
    private Set<String> categories;
    private Set<TrainerReservation> trainerReservations;
    //@TODO add schedule field

    public Trainer(String firstname, String lastname, String email, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.trainerReservations = new HashSet<>();
    }
    public Trainer() {
        this.trainerReservations = new HashSet<>();
    }


    public Set<String> getCategories() {
        return categories;
    }

    public void setTrainerReservations(Set<TrainerReservation> trainerReservations) {
        this.trainerReservations = trainerReservations;
    }

    public void setCategories(Set<String> categories) {
        this.categories = categories;
    }

    public Set<TrainerReservation> getTrainerReservations() {
        return trainerReservations;
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