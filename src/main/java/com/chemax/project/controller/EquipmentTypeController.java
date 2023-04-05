package com.chemax.project.controller;

import com.chemax.project.dto.EquipmentTypeDTO;
import com.chemax.project.request.EquipmentTypeCreateRequest;
import com.chemax.project.request.EquipmentTypeUpdateRequest;
import com.chemax.project.service.EquipmentTypeServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EquipmentTypeController {

    private final EquipmentTypeServiceImpl equipmentTypeService;

    public EquipmentTypeController(EquipmentTypeServiceImpl equipmentTypeService) {
        this.equipmentTypeService = equipmentTypeService;
    }

    @RequestMapping(value = "/addEquipmentType", method = RequestMethod.GET)
    public String showCreateEquipmentTypePage(Model model) {
        EquipmentTypeCreateRequest equipmentTypeCreateRequest = new EquipmentTypeCreateRequest();
        model.addAttribute("equipmentTypeCreateRequest", equipmentTypeCreateRequest);
        return "createEquipmentTypePage";
    }

    @RequestMapping(value = "/addEquipmentType", method = RequestMethod.POST)
    public String createEquipmentType(@ModelAttribute("equipmentTypeCreateRequest")
                                                    EquipmentTypeCreateRequest equipmentTypeCreateRequest) {
        equipmentTypeService.createEquipmentType(equipmentTypeCreateRequest);
        return "redirect:/allEquipmentType";
    }

    @GetMapping("/allEquipmentType")
    public String getAllEquipmentTypes(Model model) {
        List<EquipmentTypeDTO> equipmentTypeDTOList = equipmentTypeService.getAllEquipmentTypeDTOs();
        model.addAttribute("equipmentTypeDTOList", equipmentTypeDTOList);
        return "equipmentTypeList";
    }

    @GetMapping("/equipmentType/delete")
    public String deleteEquipmentType(@RequestParam Integer id) {
        if (!equipmentTypeService.equipmentInclusionCheck(id)) {
            return "deleteEquipmentTypeFailure";
        } else {
            equipmentTypeService.deleteEquipmentType(id);
            return "redirect:/allEquipmentType";
        }
    }

    @RequestMapping(value = "/equipmentType/update", method = RequestMethod.GET)
    public String showUpdateEquipmentTypePage (Model model, @RequestParam Integer id) {
        EquipmentTypeUpdateRequest equipmentTypeUpdateRequest = new EquipmentTypeUpdateRequest(id);
        EquipmentTypeDTO equipmentTypeDTO = equipmentTypeService.getEquipmentTypeDTOById(id);
        model.addAttribute("equipmentTypeUpdateRequest", equipmentTypeUpdateRequest);
        model.addAttribute("equipmentTypeDTO", equipmentTypeDTO);
        return "updateEquipmentTypePage";
    }

    @RequestMapping(value = "/equipmentType/update", method = RequestMethod.POST)
    public String updateEquipmentType(@ModelAttribute("equipmentTypeUpdateRequest")
                                                  EquipmentTypeUpdateRequest equipmentTypeUpdateRequest,
                                      @ModelAttribute("equipmentTypeDTO") EquipmentTypeDTO equipmentTypeDTO) {
        equipmentTypeService.updateEquipmentType(equipmentTypeUpdateRequest);
        return "redirect:/allEquipmentType";
    }
}
