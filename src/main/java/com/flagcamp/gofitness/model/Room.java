package com.flagcamp.gofitness.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private ObjectId id;
    @Field("email_one")
    private String emailOne;
    @Field("email_two")
    private String emailTwo;

    public Room() {

    }
    public Room(ObjectId id, String emailOne, String emailTwo) {
        this.id = id;
        this.emailOne = emailOne;
        this.emailTwo = emailTwo;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getEmailOne() {
        return emailOne;
    }

    public void setEmailOne(String emailOne) {
        this.emailOne = emailOne;
    }

    public String getEmailTwo() {
        return emailTwo;
    }

    public void setEmailTwo(String emailTwo) {
        this.emailTwo = emailTwo;
    }
}
