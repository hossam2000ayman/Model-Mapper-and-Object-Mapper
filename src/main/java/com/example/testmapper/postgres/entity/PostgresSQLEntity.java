package com.example.testmapper.postgres.entity;

import com.example.testmapper.model.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "postgres_entity")
@Setter
@Getter
public class PostgresSQLEntity extends BaseEntity {
    private com.example.testmapper.model.Entity entity;

}
