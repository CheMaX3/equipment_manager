package com.chemax.project.controller;

import com.chemax.project.dto.SectionDTO;
import com.chemax.project.entities.SectionEntity;
import com.chemax.project.request.SectionRequest;
import com.chemax.project.service.SectionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SectionRequestController {
    private final SectionService service;

    public SectionRequestController(SectionService service) {
        this.service = service;
    }

    @GetMapping("/allSection")
    public String getAll (Model model) {
        List<SectionDTO> sectionDTOList = service.getAllSectionDTOs();
        model.addAttribute("sectionDTOs", sectionDTOList);
        return "sectionList";
    }

    @RequestMapping(value = "/addSection", method = RequestMethod.GET)
    public String showSectionRequestPage(Model model) {
        SectionRequest sectionRequest = new SectionRequest();
        model.addAttribute("sectionRequest", sectionRequest);
        return "addSection";
    }


    @RequestMapping(value = "/addSection", method = RequestMethod.POST)
    public String createSectionEntity(Model model, @ModelAttribute("sectionRequest") SectionRequest sectionRequest) {
        String sectionFullName = sectionRequest.getSectionFullName();
        String sectionShortName = sectionRequest.getSectionShortName();
        String sectionConversationalName = sectionRequest.getSectionConversationalName();
        
//        SectionDTO sectionDTO = service.createSectionEntity(sectionRequest);
        return "createSectionForm";
    }

    @GetMapping("/section/{id}")
    public SectionDTO getSectionDTO(@PathVariable Integer id) {
        return service.getSectionDTO(id);
    }

    @GetMapping("/section/delete/{id}")
    public void deleteSectionEntity(@PathVariable Integer id) {
        service.deleteSectionEntity(id);
    }

    @PutMapping("/section/update")
    public SectionDTO updateSectionEntity(Model model, @RequestBody SectionRequest request, @RequestParam Integer id) {
        service.updateSectionEntity(request, id);
        return getSectionDTO(id);
    }

    @GetMapping("/section/showAll")
    public List<SectionDTO> getAllSectionDTOs() {
        return service.getAllSectionDTOs();
    }

    @GetMapping("/section/showAll/{count}")
    public List<SectionDTO> getSectionDTOsByCount(@PathVariable Integer count) {
        return service.getSectionDTOsByCount(count);
    }
}
