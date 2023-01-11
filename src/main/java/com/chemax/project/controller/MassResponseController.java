package com.chemax.project.controller;

import com.chemax.project.entities.SectionEntity;
import com.chemax.project.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MassResponseController {
    private final TestService service;

    public MassResponseController (TestService service) {
        this.service = service;
    }

    @GetMapping("/showAll")
    public List<SectionEntity> getAllSectionEntities() {
        return service.getAllSectionEntities();
    }

    @GetMapping("/showAll/{count}")
    public List<SectionEntity> getSectionEntitiesByCount(@PathVariable Integer count) {
        return service.getSectionEntitiesByCount(count);
    }
}
