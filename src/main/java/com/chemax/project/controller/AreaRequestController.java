package com.chemax.project.controller;

import com.chemax.project.dto.AreaDTO;
import com.chemax.project.dto.SectionDTO;
import com.chemax.project.request.AreaRequest;
import com.chemax.project.service.AreaService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AreaRequestController {
    private final AreaService service;

    public AreaRequestController(AreaService service) {
        this.service = service;
    }

    @GetMapping("/allarea")
    public String getAll (Model model) {
        List<AreaDTO> areaDTOList = service.getAllAreaDTOs();
        model.addAttribute("areadtos", areaDTOList);
        return "arealist";
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
