package com.example.testmapper.model;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "entity_id")
    private String entityId;
    @Column(name = "item_id")
    private String itemId;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;


    @LastModifiedDate
    @Column(name = "last_modified_at")
    private LocalDateTime lastModifiedAt;


    @PrePersist
    public void onCreate() {
        itemId = String.format("%s.%s", entityId, id);
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        lastModifiedAt = LocalDateTime.now();
    }

}
