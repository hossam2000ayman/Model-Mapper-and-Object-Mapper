package com.example.testmapper.mysql.entity;

import com.example.testmapper.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "mysql_entity")
@Setter
@Getter
public class MySQLEntity extends BaseEntity {

    @Column(name = "entity_name")
    private String entity;

    @Column(name = "field", columnDefinition = "json")
    private String field;// json string to hold dynamic fields


    @OneToMany(mappedBy = "mySQLEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "mysql-entity-to-fields")
    private List<MySQLEntityField> fields;

    @OneToMany(mappedBy = "mysqlEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "mysql-entity-to-methods")
    private List<MySQLEntityMethod> methods;


    @Entity
    @Table(name = "my_sql_entity_fields")
    @Setter
    @Getter
    public class MySQLEntityField {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "field_name")
        private String fieldName;

        @Column(name = "field_value")
        private String fieldValue;

        @ManyToOne
        @JsonBackReference(value = "mysql-entity-to-fields")
        @JoinColumn(name = "entity_id", nullable = false)
        private MySQLEntity mySQLEntity;


    }


    @Entity
    @Table(name = "mysql_entity_methods")
    @Setter
    @Getter
    public class MySQLEntityMethod {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "method_name")
        private String methodName;

        @Column(name = "return_type")
        private String returnType;

        @Column(name = "argument_types")
        private String argumentTypes;

        @ManyToOne
        @JsonBackReference(value = "mysql-entity-to-methods")
        @JoinColumn(name = "entity_id", nullable = false)
        private MySQLEntity mysqlEntity;
    }
}
