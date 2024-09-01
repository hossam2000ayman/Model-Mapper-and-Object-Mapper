package com.example.testmapper.postgres.entity;

import com.example.testmapper.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "postgres_entity")
@Setter
@Getter
public class PostgresSQLEntity extends BaseEntity {

    @Column(name = "entity_name")
    private String entity;

    @Column(name = "field", columnDefinition = "json")
    private String field;

    @OneToMany(mappedBy = "postgresSQLEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "postgres-entity-to-fields")
    private List<PostgresSQLEntityField> fields;
    @OneToMany(mappedBy = "postgresSQLEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "postgres-entity-to-methods")
    private List<PostgresSQLEntityMethod> methods;

    @Entity
    @Table(name = "postgres_entity_fields")
    @Setter
    @Getter
    public class PostgresSQLEntityField {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "field_name")
        private String fieldName;

        @Column(name = "field_value")
        private String fieldValue;

        @ManyToOne
        @JsonBackReference(value = "postgres-entity-to-fields")
        @JoinColumn(name = "entity_id", nullable = false)
        private PostgresSQLEntity postgresSQLEntity;


    }

    @Entity
    @Table(name = "postgres_entity_methods")
    @Setter
    @Getter
    public class PostgresSQLEntityMethod {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "method_name")
        private String methodName;

        @Column(name = "argument_types")
        private String returnType;

        @ManyToOne
        @JsonBackReference(value = "postgres-entity-to-methods")
        @JoinColumn(name = "entity_id", nullable = false)
        private PostgresSQLEntity postgresSQLEntity;

    }

}


