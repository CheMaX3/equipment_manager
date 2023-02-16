package com.chemax.project.repository;

import com.chemax.project.entities.EquipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentEntityRepository extends JpaRepository<EquipmentEntity, Integer> {
}
