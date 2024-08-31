package com.example.testmapper.postgres.repository;

import com.example.testmapper.postgres.entity.PostgresSQLEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostgresSQLRepository extends JpaRepository<PostgresSQLEntity, Long> {
}
