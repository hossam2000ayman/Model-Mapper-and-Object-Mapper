package com.example.testmapper.h2.repository;

import com.example.testmapper.h2.entity.H2Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface H2Repository extends JpaRepository<H2Entity, Long> {
}
