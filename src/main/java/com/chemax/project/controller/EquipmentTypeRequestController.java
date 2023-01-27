package com.chemax.project.controller;


import com.chemax.project.dto.AreaDTO;
import com.chemax.project.dto.EquipmentTypeDTO;
import com.chemax.project.request.EquipmentTypeRequest;
import com.chemax.project.service.EquipmentTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EquipmentTypeRequestController {
    private final EquipmentTypeService service;

    @GetMapping("/allEquipmentType")
    public String getAll (Model model) {
        List<EquipmentTypeDTO> equipmentTypeDTOList = service.getAllEquipmentTypeDTOs();
        model.addAttribute("equipmentTypeDTOs", equipmentTypeDTOList);
        return "equipmentTypeList";
    }

    public EquipmentTypeRequestController(EquipmentTypeService service) {
        this.service = service;
    }

    @PostMapping("/addEquipmentType")
    public EquipmentTypeDTO createEquipmentTypeEntity (@RequestBody EquipmentTypeRequest request) {
        return service.createEquipmentTypeEntity(request);
    }

    @GetMapping("/equipmentType/{id}")
    public EquipmentTypeDTO getEquipmentTypeDTO (@PathVariable Integer id) {return service.getEquipmentTypeDTO(id);}

    @GetMapping("/equipmentType/delete/{id}")
    public void deleteEquipmentTypeEntity (@PathVariable Integer id) {service.deleteEquipmentTypeEntity(id);}

    @PutMapping ("/equipmentType/update/{id}")
    public EquipmentTypeDTO updateEquipmentTypeEntity (@RequestBody EquipmentTypeRequest request, @PathVariable Integer id) {
        service.updateEquipmentTypeEntity(request, id);
        return getEquipmentTypeDTO(id);
    }

    @GetMapping("/equipmentType/showAll/{count}")
    public List<EquipmentTypeDTO> getEquipmentTypeDTOsByCount (@PathVariable Integer count) { return service.getEquipmentTypeDTOsByCount(count); }

}
