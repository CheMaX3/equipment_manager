package com.chemax.project.controller;

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

    public EquipmentRequestController(EquipmentService service) {
        this.service = service;
    }

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

    @RequestMapping(value = "/addEquipment", method = RequestMethod.GET)
    public String showAddEquipmentPage(Model model) {
        EquipmentRequest equipmentRequest = new EquipmentRequest();
        model.addAttribute("equipmentRequest", equipmentRequest);
        return "equipmentRequestPage";
    }

    @RequestMapping(value = "/addEquipment", method = RequestMethod.POST)
    public String createEquipmentEntity(@ModelAttribute("equipmentRequest") EquipmentRequest equipmentRequest) {
        service.createEquipmentEntity(equipmentRequest);
        return "redirect:/allEquipment";
    }

    @GetMapping("/equipment/delete")
    public String deleteEquipmentEntity(@RequestParam Integer id) {
        service.deleteEquipmentEntity(id);
        return "redirect:/allEquipment";
    }

    @RequestMapping(value = "/equipment/update", method = RequestMethod.GET)
    public String showUpdateEquipmentPage (Model model, @RequestParam Integer id) {
        EquipmentDTO equipmentDTO = service.getEquipmentDTO(id);
        model.addAttribute("equipmentDTO", equipmentDTO);
        return "equipmentUpdateRequestPage";
    }

    @RequestMapping(value = "/equipment/update", method = RequestMethod.POST)
    public String updateEquipmentEntity (@ModelAttribute("equipmentDTO") EquipmentDTO equipmentDTO,
                                         @RequestParam Integer id) {
        service.updateEquipmentEntity(equipmentDTO, id);
        return "redirect:/allEquipment";
    }
}
