package com.chemax.project.controller;

import com.chemax.project.dto.SectionDTO;
import com.chemax.project.request.SectionCreateRequest;
import com.chemax.project.request.SectionUpdateRequest;
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

    @RequestMapping(value = "/addSection", method = RequestMethod.GET)
    public String showCreateSectionPage(Model model) {
        SectionCreateRequest sectionCreateRequest = new SectionCreateRequest();
        model.addAttribute("sectionCreateRequest", sectionCreateRequest);
        return "createSectionPage";
    }

    @RequestMapping(value = "/addSection", method = RequestMethod.POST)
    public String createSection(@ModelAttribute("sectionCreateRequest") SectionCreateRequest sectionCreateRequest) {
        service.createSection(sectionCreateRequest);
        return "redirect:/allSection";
    }

    @GetMapping("/allSection")
    public String getAll (Model model) {
        List<SectionDTO> sectionDTOList = service.getAllSectionDTOs();
        model.addAttribute("sectionDTOs", sectionDTOList);
        return "sectionList";
    }

    @GetMapping("/section/delete")
    public String deleteSection(@RequestParam Integer id) {
        if (!service.areaInclusionCheck(id)) {
            return "sectionDeleteFailure";
        } else {
            service.deleteSection(id);
            return "redirect:/allSection";
        }
    }

    @RequestMapping(value = "/section/update", method = RequestMethod.GET)
    public String showUpdateSectionPage (Model model, @RequestParam Integer id) {
        SectionUpdateRequest sectionUpdateRequest = new SectionUpdateRequest(id);
        model.addAttribute("sectionUpdateRequest", sectionUpdateRequest);
        return "updateSectionPage";
    }

    @RequestMapping(value = "/section/update", method = RequestMethod.POST)
    public String updateSection (@ModelAttribute("sectionUpdateRequest") SectionUpdateRequest sectionUpdateRequest) {
        service.updateSection(sectionUpdateRequest);
        return "redirect:/allSection";
    }
}
