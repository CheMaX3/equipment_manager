package com.chemax.project.controller;

import com.chemax.project.dto.AreaDTO;
import com.chemax.project.request.AreaRequest;
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

    @PostMapping("/addArea")
    public AreaDTO createAreaEntity (@RequestBody AreaRequest request) {
        return service.createAreaEntity(request);
    }

    @GetMapping("/area/{id}")
    public AreaDTO getAreaDTO (@PathVariable Integer id) {return service.getAreaDTO(id);}

    @GetMapping("/area/delete/{id}")
    public void deleteAreaEntity (@PathVariable Integer id) {service.deleteAreaEntity(id);}

    @PutMapping ("/area/update/{id}")
    public AreaDTO updateAreaEntity (@RequestBody AreaRequest request, @PathVariable Integer id) {
        service.updateAreaEntity(request, id);
        return getAreaDTO(id);
    }

    @GetMapping("/area/showAll/{count}")
    public List<AreaDTO> getAreaDTOsByCount(@PathVariable Integer count) {
        return service.getAreaDTOsByCount(count);
    }
}
