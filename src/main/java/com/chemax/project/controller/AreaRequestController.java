package com.chemax.project.controller;

import com.chemax.project.dto.AreaDTO;
import com.chemax.project.dto.SectionDTO;
import com.chemax.project.request.AreaRequest;
import com.chemax.project.request.SectionRequest;
import com.chemax.project.service.AreaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AreaRequestController {
    private final AreaService service;

    public AreaRequestController(AreaService service) {
        this.service = service;
    }

    @GetMapping("/allArea")
    public String getAll (Model model) {
        List<AreaDTO> areaDTOList = service.getAllAreaDTOs();
        model.addAttribute("areaDTOs", areaDTOList);
        return "areaList";
    }

    @GetMapping("/allAreaBySectionId")
    public String getAllAreaSelectedSection (Model model, @RequestParam Integer id) {
        List<AreaDTO> areaSelectedSectionDTOList = service.getAllAreaSelectedSectionDTOs(id);
        model.addAttribute("areaDTOs", areaSelectedSectionDTOList);
        return "areaList";
    }

    @RequestMapping(value = "/addArea", method = RequestMethod.GET)
    public String showAddAreaPage(Model model) {
        AreaRequest areaRequest = new AreaRequest();
        model.addAttribute("areaRequest", areaRequest);
        return "areaRequestPage";
    }

    @RequestMapping(value = "/addArea", method = RequestMethod.POST)
    public String createAreaEntity(@ModelAttribute("areaRequest") AreaRequest areaRequest) {
        service.createAreaEntity(areaRequest);
        return "redirect:/allArea";
    }

    @GetMapping("/area/{id}")
    public AreaDTO getAreaDTO (@PathVariable Integer id) {return service.getAreaDTO(id);}

    @GetMapping("/area/delete")
    public String deleteAreaEntity(@RequestParam Integer id) {
        service.deleteAreaEntity(id);
        return "redirect:/allArea";
    }

    @RequestMapping(value = "/area/update", method = RequestMethod.GET)
    public String showUpdateAreaPage (Model model, @RequestParam Integer id) {
        AreaDTO areaDTO = service.getAreaDTO(id);
        model.addAttribute("areaDTO", areaDTO);
        return "areaUpdateRequestPage";
    }

    @RequestMapping(value = "/area/update", method = RequestMethod.POST)
    public String updateAreaEntity (@ModelAttribute("areaDTO") AreaDTO areaDTO, @RequestParam Integer id) {
        service.updateAreaEntity(areaDTO, id);
        return "redirect:/allArea";
    }

    @GetMapping("/area/showAll/{count}")
    public List<AreaDTO> getAreaDTOsByCount(@PathVariable Integer count) {
        return service.getAreaDTOsByCount(count);
    }
}
