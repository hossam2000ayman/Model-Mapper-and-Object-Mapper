package com.example.testmapper.mongo.entity;

import com.example.testmapper.model.Entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document(value = "mongo_entity")
public class MongoEntity {
    private Entity entity;
}
