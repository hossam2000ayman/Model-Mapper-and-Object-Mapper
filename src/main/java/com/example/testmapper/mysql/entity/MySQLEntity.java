package com.example.testmapper.mysql.entity;

import com.example.testmapper.model.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "mysql_entity")
@Setter
@Getter
public class MySQLEntity extends BaseEntity {

    private com.example.testmapper.model.Entity entity;
}
