package com.chemax.project.repository;

import com.chemax.project.entities.SectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionEntityRepository extends JpaRepository<SectionEntity, Integer> {

}
