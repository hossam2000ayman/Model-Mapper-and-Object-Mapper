package com.example.testmapper.mysql.repository;

import com.example.testmapper.mysql.entity.MySQLEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MySQLRepository extends JpaRepository<MySQLEntity, Long> {
}
