package com.chemax.project.repository;

import com.chemax.project.entities.EquipmentTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentTypeEntityRepository extends JpaRepository <EquipmentTypeEntity, Integer> {
}
