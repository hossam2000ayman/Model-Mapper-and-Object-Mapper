package com.example.testmapper.sqlserver.repository;

import com.example.testmapper.sqlserver.entity.SqlServerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SqlServerRepository extends JpaRepository<SqlServerEntity, Long> {
}
