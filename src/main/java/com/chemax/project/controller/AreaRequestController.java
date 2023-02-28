package com.chemax.project.controller;

import com.chemax.project.dto.AreaDTO;
import com.chemax.project.dto.SectionDTO;
import com.chemax.project.request.AreaRequest;
import com.chemax.project.service.AreaService;
import com.chemax.project.service.SectionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AreaRequestController {
    private final AreaService areaService;
    private final SectionService sectionService;

    public AreaRequestController(AreaService areaService, SectionService sectionService) {
        this.areaService = areaService;
        this.sectionService = sectionService;
    }

    @GetMapping("/allArea")
    public String getAll (Model model) {
        List<AreaDTO> areaDTOList = areaService.getAllAreaDTOs();
        model.addAttribute("areaDTOs", areaDTOList);
        return "areaList";
    }

    @GetMapping("/allAreaBySectionId")
    public String getAllAreaSelectedSection (Model model, @RequestParam Integer id) {
        List<AreaDTO> areaSelectedSectionDTOList = areaService.getAllAreaSelectedSectionDTOs(id);
        model.addAttribute("areaDTOs", areaSelectedSectionDTOList);
        return "areaList";
    }

    @RequestMapping(value = "/addArea", method = RequestMethod.GET)
    public String showAddAreaPage(Model model) {
        AreaRequest areaRequest = new AreaRequest();
        List<SectionDTO> sectionDTOList = sectionService.getAllSectionDTOs();
        model.addAttribute("areaRequest", areaRequest);
        model.addAttribute("sectionDTOList", sectionDTOList);
        return "areaRequestPage";
    }

    @RequestMapping(value = "/addArea", method = RequestMethod.POST)
    public String createAreaEntity(@ModelAttribute("areaRequest") AreaRequest areaRequest, Model model) {
        areaService.createAreaEntity(areaRequest);
        return "redirect:/allArea";
    }

    @GetMapping("/area/delete")
    public String deleteAreaEntity(@RequestParam Integer id) {
        if (!areaService.deleteAreaEntity(id)) {
            return "areaDeleteFailure";
        }
        return "redirect:/allArea";
    }

    @RequestMapping(value = "/area/update", method = RequestMethod.GET)
    public String showUpdateAreaPage (Model model, @RequestParam Integer id) {
        AreaDTO areaDTO = areaService.getAreaDTO(id);
        List<SectionDTO> sectionDTOList = sectionService.getAllSectionDTOs();
        model.addAttribute("areaDTO", areaDTO);
        model.addAttribute("sectionDTOList", sectionDTOList);
        return "areaUpdateRequestPage";
    }

    @RequestMapping(value = "/area/update", method = RequestMethod.POST)
    public String updateAreaEntity (@ModelAttribute("areaDTO") AreaDTO areaDTO, @RequestParam Integer id) {
        areaService.updateAreaEntity(areaDTO, id);
        return "redirect:/allArea";
    }
}
