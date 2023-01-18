package com.chemax.project.controller;

import com.chemax.project.dto.AreaDTO;
import com.chemax.project.dto.EquipmentDTO;
import com.chemax.project.dto.EquipmentTypeDTO;
import com.chemax.project.dto.SectionDTO;
import com.chemax.project.service.MainService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MassResponseController {
    private final MainService service;

    public MassResponseController (MainService service) {
        this.service = service;
    }

    @GetMapping("/section/showAll")
    public List<SectionDTO> getAllSectionDTOs() {
        return service.getAllSectionDTOs();
    }

    @GetMapping("/section/showAll/{count}")
    public List<SectionDTO> getSectionDTOsByCount(@PathVariable Integer count) {
        return service.getSectionDTOsByCount(count);
    }

    @GetMapping("/area/showAll/{count}")
    public List<AreaDTO> getAreaDTOsByCount(@PathVariable Integer count) {
        return service.getAreaDTOsByCount(count);
    }

    @GetMapping("/equipmentType/showAll/{count}")
    public List<EquipmentTypeDTO> getEquipmentTypeDTOsByCount (@PathVariable Integer count) { return service.getEquipmentTypeDTOsByCount(count); }

    @GetMapping("/equipment/showAll/{count}")
    public List<EquipmentDTO> getEquipmentDTOsByCount (@PathVariable Integer count) { return service.getEquipmentDTOsByCount(count); }
}
