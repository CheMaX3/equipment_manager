package com.chemax.project.repository;

import com.chemax.project.entity.EquipmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentTypeRepository extends JpaRepository <EquipmentType, Integer> {
}
