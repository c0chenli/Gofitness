package com.flagcamp.gofitness.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class ScheduleDaoImpl implements ScheduleDao {

    @Autowired
    private MongoTemplate mongoTemplate;
}
