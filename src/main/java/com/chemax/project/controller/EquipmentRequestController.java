package com.chemax.project.controller;

import com.chemax.project.dto.AreaDTO;
import com.chemax.project.dto.EquipmentDTO;
import com.chemax.project.dto.EquipmentTypeDTO;
import com.chemax.project.request.EquipmentRequest;
import com.chemax.project.service.AreaService;
import com.chemax.project.service.EquipmentService;
import com.chemax.project.service.EquipmentTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class EquipmentRequestController {
    private final EquipmentService equipmentService;
    private final AreaService areaService;
    private final EquipmentTypeService equipmentTypeService;

    public EquipmentRequestController(EquipmentService equipmentService, AreaService areaService, EquipmentTypeService equipmentTypeService) {
        this.equipmentService = equipmentService;
        this.areaService = areaService;
        this.equipmentTypeService = equipmentTypeService;
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
        List<AreaDTO> areaDTOList = areaService.getAllAreaDTOs();
        List<EquipmentTypeDTO> equipmentTypeDTOList = equipmentTypeService.getAllEquipmentTypeDTOs();
        model.addAttribute("equipmentRequest", equipmentRequest);
        model.addAttribute("areaDTOList", areaDTOList);
        model.addAttribute("equipmentTypeDTOList", equipmentTypeDTOList);
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
        List<AreaDTO> areaDTOList = areaService.getAllAreaDTOs();
        List<EquipmentTypeDTO> equipmentTypeDTOList = equipmentTypeService.getAllEquipmentTypeDTOs();
        model.addAttribute("equipmentDTO", equipmentDTO);
        model.addAttribute("areaDTOList", areaDTOList);
        model.addAttribute("equipmentTypeDTOList", equipmentTypeDTOList);
        return "equipmentUpdateRequestPage";
    }

    @RequestMapping(value = "/equipment/update", method = RequestMethod.POST)
    public String updateEquipmentEntity (@ModelAttribute("equipmentDTO") EquipmentDTO equipmentDTO,
                                         @RequestParam Integer id) {
        equipmentService.updateEquipmentEntity(equipmentDTO, id);
        return "redirect:/allEquipment";
    }
}
