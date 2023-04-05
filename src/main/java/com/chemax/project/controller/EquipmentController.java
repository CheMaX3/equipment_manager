package com.chemax.project.controller;

import com.chemax.project.dto.AreaDTO;
import com.chemax.project.dto.EquipmentDTO;
import com.chemax.project.dto.EquipmentTypeDTO;
import com.chemax.project.request.EquipmentCreateRequest;
import com.chemax.project.request.EquipmentUpdateRequest;
import com.chemax.project.service.AreaServiceImpl;
import com.chemax.project.service.EquipmentServiceImpl;
import com.chemax.project.service.EquipmentTypeServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class EquipmentController {

    private final EquipmentServiceImpl equipmentService;
    private final AreaServiceImpl areaService;
    private final EquipmentTypeServiceImpl equipmentTypeService;

    public EquipmentController(EquipmentServiceImpl equipmentService, AreaServiceImpl areaService,
                               EquipmentTypeServiceImpl equipmentTypeService) {
        this.equipmentService = equipmentService;
        this.areaService = areaService;
        this.equipmentTypeService = equipmentTypeService;
    }

    @RequestMapping(value = "/addEquipment", method = RequestMethod.GET)
    public String showCreateEquipmentPage(Model model) {
        EquipmentCreateRequest equipmentCreateRequest = new EquipmentCreateRequest();
        List<AreaDTO> areaDTOList = areaService.getAllAreaDTOs();
        List<EquipmentTypeDTO> equipmentTypeDTOList = equipmentTypeService.getAllEquipmentTypeDTOs();
        model.addAttribute("equipmentCreateRequest", equipmentCreateRequest);
        model.addAttribute("areaDTOList", areaDTOList);
        model.addAttribute("equipmentTypeDTOList", equipmentTypeDTOList);
        return "createEquipmentPage";
    }

    @RequestMapping(value = "/addEquipment", method = RequestMethod.POST)
    public String createEquipment(@ModelAttribute("equipmentCreateRequest")
                                              EquipmentCreateRequest equipmentCreateRequest) {
        equipmentService.createEquipment(equipmentCreateRequest);
        return "redirect:/allEquipment";
    }


    @GetMapping("/allEquipment")
    public String getAllEquipment(Model model) {
        List<EquipmentDTO> equipmentDTOList = equipmentService.getAllEquipmentDTOs();
        model.addAttribute("equipmentDTOList", equipmentDTOList);
        return "equipmentList";
    }

    @GetMapping("/allEquipmentByAreaId")
    public String getAllEquipmentByAreaId(Model model, @RequestParam Integer id) {
        List<EquipmentDTO> equipmentByAreaId = equipmentService.getEquipmentListByAreaId(id);
        model.addAttribute("equipmentDTOList", equipmentByAreaId);
        return "equipmentList";
    }

    @GetMapping("/allEquipmentByEquipmentTypeId")
    public String getAllEquipmentByEquipmentTypeId(Model model, @RequestParam Integer id) {
        List<EquipmentDTO> equipmentByEquipmentTypeId = equipmentService.getEquipmentListByEquipmentTypeId(id);
        model.addAttribute("equipmentDTOList", equipmentByEquipmentTypeId);
        return "equipmentList";
    }

    @GetMapping("/equipment/delete")
    public String deleteEquipment(@RequestParam Integer id) {
        equipmentService.deleteEquipment(id);
        return "redirect:/allEquipment";
    }

    @RequestMapping(value = "/equipment/update", method = RequestMethod.GET)
    public String showUpdateEquipmentPage (Model model, @RequestParam Integer id) {
        EquipmentUpdateRequest equipmentUpdateRequest = new EquipmentUpdateRequest(id);
        List<AreaDTO> areaDTOList = areaService.getAllAreaDTOs();
        List<EquipmentTypeDTO> equipmentTypeDTOList = equipmentTypeService.getAllEquipmentTypeDTOs();
        EquipmentDTO equipmentDTO = equipmentService.getEquipmentDTO(id);
        model.addAttribute("equipmentUpdateRequest", equipmentUpdateRequest);
        model.addAttribute("areaDTOList", areaDTOList);
        model.addAttribute("equipmentDTO", equipmentDTO);
        model.addAttribute("equipmentTypeDTOList", equipmentTypeDTOList);
        return "updateEquipmentPage";
    }

    @RequestMapping(value = "/equipment/update", method = RequestMethod.POST)
    public String updateEquipment(@ModelAttribute("equipmentUpdateRequest")
                                              EquipmentUpdateRequest equipmentUpdateRequest,
                                  @ModelAttribute("equipmentDTO") EquipmentDTO equipmentDTO) {
        equipmentService.updateEquipment(equipmentUpdateRequest);
        return "redirect:/allEquipment";
    }
}
