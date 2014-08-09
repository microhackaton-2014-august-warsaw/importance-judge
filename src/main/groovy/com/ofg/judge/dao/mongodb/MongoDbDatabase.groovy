package com.ofg.judge.dao.mongodb

import com.mongodb.DB
import com.mongodb.DBCollection
import com.mongodb.MongoClient
import com.ofg.judge.dao.JudgeDAO
import com.ofg.twitter.controller.relations.Relationship
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired

import javax.annotation.PostConstruct

@Slf4j
class MongoDbDatabase implements JudgeDAO {

    private final MongoClient mongoClient
    private DB db

    @Autowired
    MongoDbDatabase(MongoClient mongoClient) {
        this.mongoClient = mongoClient
    }

    @PostConstruct
    public void start() {
        db = mongoClient.getDB("importance-judge")
    }

    @Override
    Relationship updateRelationship(Relationship newRelationship) {
        log.info("Storing: ")
        final DBCollection relationships = db.getCollection("relationships")
    }
}
