package com.chemax.project.controller;

import com.chemax.project.dto.SectionEntityDTO;
import com.chemax.project.entities.SectionEntity;
import com.chemax.project.request.SectionRequest;
import com.chemax.project.service.MainService;
import org.springframework.web.bind.annotation.*;


@RestController
public class MainController {
    private final MainService service;

    public MainController(MainService service) {
        this.service = service;
    }

    @PostMapping("/addSection")
    public SectionEntity createSectionEntity (@RequestBody SectionRequest request) {
        return service.createSectionEntity(request);
    }

    @GetMapping("/entity/{id}")
    public SectionEntity getSectionEntity (@PathVariable Integer id) {
        return service.getSectionEntity(id);
    }

    @GetMapping("/{id}")
    public SectionEntityDTO getSectionEntityDTO (@PathVariable Integer id) {
        return service.getSectionEntityDTO(id);
    }

    @GetMapping("/delete/{id}")
    public void deleteSectionEntity (@PathVariable Integer id) {
        service.deleteSectionEntity(id);
    }

    @PutMapping ("/update/{id}")
    public void updateSectionEntity (@RequestBody SectionRequest request, @PathVariable Integer id) {
        service.updateSectionEntity(request, id);
    }
}
