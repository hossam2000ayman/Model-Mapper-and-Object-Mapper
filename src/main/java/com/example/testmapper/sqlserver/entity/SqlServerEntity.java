package com.example.testmapper.sqlserver.entity;

import com.example.testmapper.model.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "sqlserver_entity")
public class SqlServerEntity extends BaseEntity {
    private com.example.testmapper.model.Entity entity;
}
