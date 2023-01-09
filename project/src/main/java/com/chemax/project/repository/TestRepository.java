package com.chemax.project.repository;

import com.chemax.project.request.SectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<SectionEntity, Integer> {

}
