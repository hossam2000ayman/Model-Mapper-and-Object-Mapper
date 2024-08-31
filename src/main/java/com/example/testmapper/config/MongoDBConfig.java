package com.example.testmapper.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
@Profile("mongodb")
public class MongoDBConfig {

    @Bean(name = "mongoProperties")
    @ConfigurationProperties(prefix = "spring.data.mongodb")
    public MongoProperties mongoProperties() {
        return new MongoProperties();
    }

    @Bean(name = "mongoDatabaseFactory")
    public MongoDatabaseFactory mongoDatabaseFactory(@Qualifier("mongoProperties") MongoProperties mongoProperties) {
        return new SimpleMongoClientDatabaseFactory(mongoProperties.getUri());
    }

    @Bean(name = "mongoTemplate")
    public MongoTemplate mongoTemplate(@Qualifier("mongoDatabaseFactory") MongoDatabaseFactory mongoDatabaseFactory) {
        return new MongoTemplate(mongoDatabaseFactory);
    }


}
