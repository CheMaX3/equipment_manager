package com.chemax.project.controller;

import com.chemax.project.dto.AreaDTO;
import com.chemax.project.dto.EquipmentDTO;
import com.chemax.project.request.EquipmentRequest;
import com.chemax.project.service.EquipmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class EquipmentRequestController {
    private final EquipmentService service;

    @GetMapping("/allEquipment")
    public String getAll (Model model) {
        List<EquipmentDTO> equipmentDTOList = service.getAllEquipmentDTOs();
        model.addAttribute("equipmentDTOs", equipmentDTOList);
        return "equipmentList";
    }

    @GetMapping("/allEquipmentByAreaId")
    public String getAllEquipmentSelectedArea (Model model, @RequestParam Integer id) {
        List<EquipmentDTO> equipmentSelectedAreaDTOList = service.getAllEquipmentSelectedAreaDTOs(id);
        model.addAttribute("equipmentDTOs", equipmentSelectedAreaDTOList);
        return "equipmentList";
    }

    @GetMapping("/allEquipmentByMachineTypeId")
    public String getAllEquipmentSelectedMachineType (Model model, @RequestParam Integer id) {
        List<EquipmentDTO> equipmentSelectedMachineTypeDTOList = service.getAllEquipmentSelectedMachineTypeDTOs(id);
        model.addAttribute("equipmentDTOs", equipmentSelectedMachineTypeDTOList);
        return "equipmentList";
    }

    public EquipmentRequestController(EquipmentService service) {
        this.service = service;
    }

    @PostMapping("/addEquipment")
    public EquipmentDTO createEquipmentEntity (@RequestBody EquipmentRequest request) {
        return service.createEquipmentEntity(request);
    }

    @GetMapping("/equipment/{id}")
    public EquipmentDTO getEquipmentDTO (@PathVariable Integer id) {return service.getEquipmentDTO(id);}

    @GetMapping("/equipment/delete/{id}")
    public void deleteEquipmentEntity (@PathVariable Integer id) {service.deleteEquipmentEntity(id);}

    @PutMapping ("/equipment/update/{id}")
    public EquipmentDTO updateEquipmentEntity (@RequestBody EquipmentRequest request, @PathVariable Integer id) {
        service.updateEquipmentEntity(request, id);
        return getEquipmentDTO(id);
    }

    @GetMapping("/equipment/showAll/{count}")
    public List<EquipmentDTO> getEquipmentDTOsByCount (@PathVariable Integer count) { return service.getEquipmentDTOsByCount(count); }

}
