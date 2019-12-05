package com.flagcamp.gofitness.dao;

import com.flagcamp.gofitness.model.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class VideoDaoImpl implements VideoDao {

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public void addVideo(Video video) {
        mongoTemplate.save(video);
    }
}
