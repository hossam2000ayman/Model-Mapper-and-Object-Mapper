package com.example.testmapper.mongo.repository;

import com.example.testmapper.mongo.entity.MongoEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoRepository extends org.springframework.data.mongodb.repository.MongoRepository<MongoEntity,String> {
}
