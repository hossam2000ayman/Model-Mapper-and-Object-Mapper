package com.example.testmapper.mongo.entity;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Setter
@Getter
@Document(collection = "mongo_entity")
public class MongoEntity {

    @Id
    private String id;


    private String entity;

    @DBRef(lazy = true)
    private List<MongoEntityField> fields;


    @Document(collection = "mongo_entity_fields")
    @Setter
    @Getter
    public class MongoEntityField {

        @Id
        private String id;

        @Field(name = "field_name")
        private String fieldName;

        @Field(name = "field_value")
        private String fieldValue;

        private String entityId; //ID REFERENCE TO PARENT MONGO ENTITY MANUALLY

    }


    @Document(collection = "mongo_entity_methods")
    @Setter
    @Getter
    public class MongoEntityMethod {

        @Id
        private String id;

        @Field("method_name")
        private String methodName;

        @Field("return_type")
        private String returnType;

        @Field("argument_types")
        private String argumentTypes;

        private String entityId;  // ID reference to the parent MongoEntity
    }
}
