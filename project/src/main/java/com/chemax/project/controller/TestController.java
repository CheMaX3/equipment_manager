package com.chemax.project.controller;

import com.chemax.project.dto.TestDTO;
import com.chemax.project.request.SectionEntity;
import com.chemax.project.request.SectionRequest;
import com.chemax.project.service.TestService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/request")
public class TestController {
    private final TestService service;

    public TestController(TestService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public SectionEntity createSectionEntity (@RequestBody SectionRequest request) {
        return service.createSectionEntity(request);
    }

    @GetMapping("/entity/{id}")
    public SectionEntity getSectionEntity (@PathVariable Integer id) {
        return service.getSectionEntity(id);
    }

    @GetMapping("/{id}")
    public TestDTO getTestDTO (@PathVariable Integer id) {
        return service.getTestDTO(id);
    }

    @GetMapping("/delete/{id}")
    public void deleteSectionEntity (@PathVariable Integer id) {
        service.deleteSectionEntity(id);
    }

    @PostMapping("/update/{id}")
    public void updateSectionEntity (@RequestBody SectionRequest request, @PathVariable Integer id) {
        service.updateSectionEntity(request, id);
    }
}
