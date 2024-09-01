package com.example.testmapper.sqlserver.entity;

import com.example.testmapper.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "sqlserver_entity")
@Setter
@Getter
public class SqlServerEntity extends BaseEntity {

    @Column(name = "entity_name")
    private String entity;

    @Column(name = "field", columnDefinition = "json")
    private String field;

    @OneToMany(mappedBy = "sqlServerEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "sqlserver-entity-to-fields")
    private List<SqlServerEntityField> fields;
    @OneToMany(mappedBy = "sqlServerEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "sqlserver-entity-to-methods")
    private List<SqlServerEntityMethod> methods;

    @Entity
    @Table(name = "postgres_entity_fields")
    @Setter
    @Getter
    public class SqlServerEntityField {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "field_name")
        private String fieldName;

        @Column(name = "field_value")
        private String fieldValue;

        @ManyToOne
        @JsonBackReference(value = "sqlserver-entity-to-fields")
        @JoinColumn(name = "entity_id", nullable = false)
        private SqlServerEntity sqlServerEntity;


    }

    @Entity
    @Table(name = "postgres_entity_methods")
    @Setter
    @Getter
    public class SqlServerEntityMethod {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "method_name")
        private String methodName;

        @Column(name = "argument_types")
        private String returnType;

        @ManyToOne
        @JsonBackReference(value = "sqlserver-entity-to-methods")
        @JoinColumn(name = "entity_id", nullable = false)
        private SqlServerEntity sqlServerEntity;

    }
}
