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
    private final EquipmentService equipmentService;

    public EquipmentRequestController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @GetMapping("/allEquipment")
    public String getAll (Model model) {
        List<EquipmentDTO> equipmentDTOList = equipmentService.getAllEquipmentDTOs();
        model.addAttribute("equipmentDTOs", equipmentDTOList);
        return "equipmentList";
    }

    @GetMapping("/allEquipmentByAreaId")
    public String getAllEquipmentSelectedArea (Model model, @RequestParam Integer id) {
        List<EquipmentDTO> equipmentSelectedAreaDTOList = equipmentService.getAllEquipmentSelectedAreaDTOs(id);
        model.addAttribute("equipmentDTOs", equipmentSelectedAreaDTOList);
        return "equipmentList";
    }

    @GetMapping("/allEquipmentByMachineTypeId")
    public String getAllEquipmentSelectedMachineType (Model model, @RequestParam Integer id) {
        List<EquipmentDTO> equipmentSelectedMachineTypeDTOList = equipmentService.getAllEquipmentSelectedMachineTypeDTOs(id);
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
        equipmentService.createEquipmentEntity(equipmentRequest);
        return "redirect:/allEquipment";
    }

    @GetMapping("/equipment/delete")
    public String deleteEquipmentEntity(@RequestParam Integer id) {
        equipmentService.deleteEquipmentEntity(id);
        return "redirect:/allEquipment";
    }

    @RequestMapping(value = "/equipment/update", method = RequestMethod.GET)
    public String showUpdateEquipmentPage (Model model, @RequestParam Integer id) {
        EquipmentDTO equipmentDTO = equipmentService.getEquipmentDTO(id);
        model.addAttribute("equipmentDTO", equipmentDTO);
        return "equipmentUpdateRequestPage";
    }

    @RequestMapping(value = "/equipment/update", method = RequestMethod.POST)
    public String updateEquipmentEntity (@ModelAttribute("equipmentDTO") EquipmentDTO equipmentDTO,
                                         @RequestParam Integer id) {
        equipmentService.updateEquipmentEntity(equipmentDTO, id);
        return "redirect:/allEquipment";
    }
}
