package com.chemax.project.controller;

import com.chemax.project.dto.AreaDTO;
import com.chemax.project.dto.SectionDTO;
import com.chemax.project.request.AreaCreateRequest;
import com.chemax.project.request.AreaUpdateRequest;
import com.chemax.project.service.SectionServiceImpl;
import com.chemax.project.service.AreaServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AreaController {

    private final AreaServiceImpl areaService;
    private final SectionServiceImpl sectionService;

    public AreaController(AreaServiceImpl areaService, SectionServiceImpl sectionService) {
        this.areaService = areaService;
        this.sectionService = sectionService;
    }

    @RequestMapping(value = "/addArea", method = RequestMethod.GET)
    public String showCreateAreaPage(Model model) {
        AreaCreateRequest areaCreateRequest = new AreaCreateRequest();
        List<SectionDTO> sectionDTOList = sectionService.getAllSectionDTOs();
        model.addAttribute("areaCreateRequest", areaCreateRequest);
        model.addAttribute("sectionDTOList", sectionDTOList);
        return "createAreaPage";
    }

    @RequestMapping(value = "/addArea", method = RequestMethod.POST)
    public String createArea(@ModelAttribute("areaCreateRequest") AreaCreateRequest areaCreateRequest) {
        areaService.createArea(areaCreateRequest);
        return "redirect:/allArea";
    }

    @GetMapping("/allArea")
    public String getAllAreas (Model model) {
        List<AreaDTO> areaDTOList = areaService.getAllAreaDTOs();
        model.addAttribute("areaDTOs", areaDTOList);
        return "areaList";
    }

    @GetMapping("/allAreaBySectionId")
    public String getAllAreaBySectionId (Model model, @RequestParam Integer id) {
        List<AreaDTO> areaBySectionId = areaService.getAreaListBySectionId(id);
        model.addAttribute("areaDTOs", areaBySectionId);
        return "areaList";
    }

    @GetMapping("/area/delete")
    public String deleteSArea(@RequestParam Integer id) {
        if (!areaService.equipmentInclusionCheck(id)) {
            return "areaDeleteFailure";
        } else {
            areaService.deleteArea(id);
            return "redirect:/allArea";
        }
    }

    @RequestMapping(value = "/area/update", method = RequestMethod.GET)
    public String showUpdateAreaPage (Model model, @RequestParam Integer id) {
        AreaUpdateRequest areaUpdateRequest = new AreaUpdateRequest(id);
        AreaDTO areaDTO = areaService.getAreaDTOById(id);
        List<SectionDTO> sectionDTOList = sectionService.getAllSectionDTOs();
        model.addAttribute("areaUpdateRequest", areaUpdateRequest);
        model.addAttribute("sectionDTOList", sectionDTOList);
        model.addAttribute("areaDTO", areaDTO);
        return "updateAreaPage";
    }

    @RequestMapping(value = "/area/update", method = RequestMethod.POST)
    public String updateArea (@ModelAttribute("areaUpdateRequest") AreaUpdateRequest areaUpdateRequest,
                              @ModelAttribute("areaDTO") AreaDTO areaDTO) {
        areaService.updateArea(areaUpdateRequest);
        return "redirect:/allArea";
    }
}
