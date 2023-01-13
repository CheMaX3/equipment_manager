package com.chemax.project.repository;

import com.chemax.project.entities.SectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingsEntityRepository extends JpaRepository<SectionEntity, Integer> {

}
