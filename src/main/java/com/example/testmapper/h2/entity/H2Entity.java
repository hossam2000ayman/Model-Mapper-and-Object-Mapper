package com.example.testmapper.h2.entity;

import com.example.testmapper.model.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "h2_entity")
@Setter
@Getter
public class H2Entity extends BaseEntity {
    private com.example.testmapper.model.Entity entity;
}
