package com.chemax.project.repository;

import com.chemax.project.entities.AreaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaEntityRepository extends JpaRepository<AreaEntity, Integer> {
}
