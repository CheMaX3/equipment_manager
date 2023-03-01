package com.chemax.project.controller;

import com.chemax.project.dto.AreaDTO;
import com.chemax.project.dto.EquipmentDTO;
import com.chemax.project.dto.EquipmentTypeDTO;
import com.chemax.project.request.EquipmentRequest;
import com.chemax.project.service.AreaServiceImpl;
import com.chemax.project.service.EquipmentServiceImpl;
import com.chemax.project.service.EquipmentTypeServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class EquipmentController {
    private final EquipmentServiceImpl equipmentServiceImpl;
    private final AreaServiceImpl areaServiceImpl;
    private final EquipmentTypeServiceImpl equipmentTypeServiceImpl;

    public EquipmentController(EquipmentServiceImpl equipmentServiceImpl, AreaServiceImpl areaServiceImpl, EquipmentTypeServiceImpl equipmentTypeServiceImpl) {
        this.equipmentServiceImpl = equipmentServiceImpl;
        this.areaServiceImpl = areaServiceImpl;
        this.equipmentTypeServiceImpl = equipmentTypeServiceImpl;
    }

    @GetMapping("/allEquipment")
    public String getAll (Model model) {
        List<EquipmentDTO> equipmentDTOList = equipmentServiceImpl.getAllEquipmentDTOs();
        model.addAttribute("equipmentDTOs", equipmentDTOList);
        return "equipmentList";
    }

    @GetMapping("/allEquipmentByAreaId")
    public String getAllEquipmentSelectedArea (Model model, @RequestParam Integer id) {
        List<EquipmentDTO> equipmentSelectedAreaDTOList = equipmentServiceImpl.getAllEquipmentSelectedAreaDTOs(id);
        model.addAttribute("equipmentDTOs", equipmentSelectedAreaDTOList);
        return "equipmentList";
    }

    @GetMapping("/allEquipmentByMachineTypeId")
    public String getAllEquipmentSelectedMachineType (Model model, @RequestParam Integer id) {
        List<EquipmentDTO> equipmentSelectedMachineTypeDTOList = equipmentServiceImpl.getAllEquipmentSelectedMachineTypeDTOs(id);
        model.addAttribute("equipmentDTOs", equipmentSelectedMachineTypeDTOList);
        return "equipmentList";
    }

    @RequestMapping(value = "/addEquipment", method = RequestMethod.GET)
    public String showAddEquipmentPage(Model model) {
        EquipmentRequest equipmentRequest = new EquipmentRequest();
        List<AreaDTO> areaDTOList = areaServiceImpl.getAllAreaDTOs();
        List<EquipmentTypeDTO> equipmentTypeDTOList = equipmentTypeServiceImpl.getAllEquipmentTypeDTOs();
        model.addAttribute("equipmentRequest", equipmentRequest);
        model.addAttribute("areaDTOList", areaDTOList);
        model.addAttribute("equipmentTypeDTOList", equipmentTypeDTOList);
        return "equipmentRequestPage";
    }

    @RequestMapping(value = "/addEquipment", method = RequestMethod.POST)
    public String createEquipmentEntity(@ModelAttribute("equipmentRequest") EquipmentRequest equipmentRequest) {
        equipmentServiceImpl.createEquipmentEntity(equipmentRequest);
        return "redirect:/allEquipment";
    }

    @GetMapping("/equipment/delete")
    public String deleteEquipmentEntity(@RequestParam Integer id) {
        equipmentServiceImpl.deleteEquipmentEntity(id);
        return "redirect:/allEquipment";
    }

    @RequestMapping(value = "/equipment/update", method = RequestMethod.GET)
    public String showUpdateEquipmentPage (Model model, @RequestParam Integer id) {
        EquipmentDTO equipmentDTO = equipmentServiceImpl.getEquipmentDTO(id);
        List<AreaDTO> areaDTOList = areaServiceImpl.getAllAreaDTOs();
        List<EquipmentTypeDTO> equipmentTypeDTOList = equipmentTypeServiceImpl.getAllEquipmentTypeDTOs();
        model.addAttribute("equipmentDTO", equipmentDTO);
        model.addAttribute("areaDTOList", areaDTOList);
        model.addAttribute("equipmentTypeDTOList", equipmentTypeDTOList);
        return "equipmentUpdateRequestPage";
    }

    @RequestMapping(value = "/equipment/update", method = RequestMethod.POST)
    public String updateEquipmentEntity (@ModelAttribute("equipmentDTO") EquipmentDTO equipmentDTO,
                                         @RequestParam Integer id) {
        equipmentServiceImpl.updateEquipmentEntity(equipmentDTO, id);
        return "redirect:/allEquipment";
    }
}
