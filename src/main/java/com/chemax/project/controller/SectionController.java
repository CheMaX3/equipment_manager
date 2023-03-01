package com.chemax.project.controller;

import com.chemax.project.dto.SectionDTO;
import com.chemax.project.request.SectionRequest;
import com.chemax.project.service.SectionServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SectionController {
    private final SectionServiceImpl service;

    public SectionController(SectionServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/allSection")
    public String getAll (Model model) {
        List<SectionDTO> sectionDTOList = service.getAllSectionDTOs();
        model.addAttribute("sectionDTOs", sectionDTOList);
        return "sectionList";
    }

    @RequestMapping(value = "/addSection", method = RequestMethod.GET)
    public String showAddSectionPage(Model model) {
        SectionRequest sectionRequest = new SectionRequest();
        model.addAttribute("sectionRequest", sectionRequest);
        return "sectionRequestPage";
    }

    @RequestMapping(value = "/addSection", method = RequestMethod.POST)
    public String createSectionEntity(@ModelAttribute("sectionRequest") SectionRequest sectionRequest) {
        service.createSectionEntity(sectionRequest);
        return "redirect:/allSection";
    }

    @GetMapping("/section/delete")
    public String deleteSectionEntity(@RequestParam Integer id) {
        if (!service.deleteSectionEntity(id)) {
            return "sectionDeleteFailure";
        }
        return "redirect:/allSection";
    }

    @RequestMapping(value = "/section/update", method = RequestMethod.GET)
    public String showUpdateSectionPage (Model model, @RequestParam Integer id) {
        SectionDTO sectionDTO = service.getSectionDTO(id);
        model.addAttribute("sectionDTO", sectionDTO);
        return "sectionUpdateRequestPage";
    }

    @RequestMapping(value = "/section/update", method = RequestMethod.POST)
    public String updateSectionEntity (@ModelAttribute("sectionDTO") SectionDTO sectionDTO, @RequestParam Integer id) {
        service.updateSectionEntity(sectionDTO, id);
        return "redirect:/allSection";
    }
}
