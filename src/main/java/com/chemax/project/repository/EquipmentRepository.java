package com.chemax.project.repository;

import com.chemax.project.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {
    List<Equipment> findByAreaId(Integer areaId);
    List<Equipment> findByEquipmentTypeId(Integer equipmentTypeId);
}
