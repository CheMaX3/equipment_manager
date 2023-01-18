package com.chemax.project.repository;

import com.chemax.project.entities.EquipmentTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentTypeEntityRepository extends JpaRepository <EquipmentTypeEntity, Integer> {
}
