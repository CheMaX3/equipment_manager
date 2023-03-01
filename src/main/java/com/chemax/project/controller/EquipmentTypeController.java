package com.chemax.project.controller;

import com.chemax.project.dto.EquipmentTypeDTO;
import com.chemax.project.request.EquipmentTypeRequest;
import com.chemax.project.service.EquipmentTypeServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EquipmentTypeController {
    private final EquipmentTypeServiceImpl service;

    public EquipmentTypeController(EquipmentTypeServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/allEquipmentType")
    public String getAll (Model model) {
        List<EquipmentTypeDTO> equipmentTypeDTOList = service.getAllEquipmentTypeDTOs();
        model.addAttribute("equipmentTypeDTOs", equipmentTypeDTOList);
        return "equipmentTypeList";
    }

    @RequestMapping(value = "/addEquipmentType", method = RequestMethod.GET)
    public String showAddEquipmentTypePage(Model model) {
        EquipmentTypeRequest equipmentTypeRequest = new EquipmentTypeRequest();
        model.addAttribute("equipmentTypeRequest", equipmentTypeRequest);
        return "equipmentTypeRequestPage";
    }

    @RequestMapping(value = "/addEquipmentType", method = RequestMethod.POST)
    public String createEquipmentTypeEntity(@ModelAttribute("equipmentTypeRequest")
                                                        EquipmentTypeRequest equipmentTypeRequest) {
        service.createEquipmentTypeEntity(equipmentTypeRequest);
        return "redirect:/allEquipmentType";
    }

    @GetMapping("/equipmentType/delete")
    public String deleteEquipmentTypeEntity(@RequestParam Integer id) {
        if (!service.deleteEquipmentTypeEntity(id)) {
            return "deleteEquipmentTypeFailure";
        }
        return "redirect:/allEquipmentType";
    }

    @RequestMapping(value = "/equipmentType/update", method = RequestMethod.GET)
    public String showUpdateEquipmentTypePage (Model model, @RequestParam Integer id) {
        EquipmentTypeDTO equipmentTypeDTO = service.getEquipmentTypeDTO(id);
        model.addAttribute("equipmentTypeDTO", equipmentTypeDTO);
        return "equipmentTypeUpdateRequestPage";
    }

    @RequestMapping(value = "/equipmentType/update", method = RequestMethod.POST)
    public String updateEquipmentTypeEntity (@ModelAttribute("equipmentTypeDTO") EquipmentTypeDTO equipmentTypeDTO,
                                             @RequestParam Integer id) {
        service.updateEquipmentTypeEntity(equipmentTypeDTO, id);
        return "redirect:/allEquipmentType";
    }
}
