package com.ofg.judge.config;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.UnknownHostException;

@Configuration
public class PersistenceConfiguration {

    @Bean
    MongoClient mongoClient() throws UnknownHostException {
        return new MongoClient("mongo.microhackathon.pl:27017");
    }

}
