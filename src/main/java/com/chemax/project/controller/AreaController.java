package com.chemax.project.controller;

import com.chemax.project.dto.AreaDTO;
import com.chemax.project.dto.SectionDTO;
import com.chemax.project.request.AreaRequest;
import com.chemax.project.service.AreaServiceImpl;
import com.chemax.project.service.SectionServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AreaController {
    private final AreaServiceImpl areaServiceImpl;
    private final SectionServiceImpl sectionServiceImpl;

    public AreaController(AreaServiceImpl areaServiceImpl, SectionServiceImpl sectionServiceImpl) {
        this.areaServiceImpl = areaServiceImpl;
        this.sectionServiceImpl = sectionServiceImpl;
    }

    @GetMapping("/allArea")
    public String getAll (Model model) {
        List<AreaDTO> areaDTOList = areaServiceImpl.getAllAreaDTOs();
        model.addAttribute("areaDTOs", areaDTOList);
        return "areaList";
    }

    @GetMapping("/allAreaBySectionId")
    public String getAllAreaSelectedSection (Model model, @RequestParam Integer id) {
        List<AreaDTO> areaSelectedSectionDTOList = areaServiceImpl.getAllAreaSelectedSectionDTOs(id);
        model.addAttribute("areaDTOs", areaSelectedSectionDTOList);
        return "areaList";
    }

    @RequestMapping(value = "/addArea", method = RequestMethod.GET)
    public String showAddAreaPage(Model model) {
        AreaRequest areaRequest = new AreaRequest();
        List<SectionDTO> sectionDTOList = sectionServiceImpl.getAllSectionDTOs();
        model.addAttribute("areaRequest", areaRequest);
        model.addAttribute("sectionDTOList", sectionDTOList);
        return "areaRequestPage";
    }

    @RequestMapping(value = "/addArea", method = RequestMethod.POST)
    public String createAreaEntity(@ModelAttribute("areaRequest") AreaRequest areaRequest, Model model) {
        areaServiceImpl.createAreaEntity(areaRequest);
        return "redirect:/allArea";
    }

    @GetMapping("/area/delete")
    public String deleteAreaEntity(@RequestParam Integer id) {
        if (!areaServiceImpl.deleteAreaEntity(id)) {
            return "areaDeleteFailure";
        }
        return "redirect:/allArea";
    }

    @RequestMapping(value = "/area/update", method = RequestMethod.GET)
    public String showUpdateAreaPage (Model model, @RequestParam Integer id) {
        AreaDTO areaDTO = areaServiceImpl.getAreaDTO(id);
        List<SectionDTO> sectionDTOList = sectionServiceImpl.getAllSectionDTOs();
        model.addAttribute("areaDTO", areaDTO);
        model.addAttribute("sectionDTOList", sectionDTOList);
        return "areaUpdateRequestPage";
    }

    @RequestMapping(value = "/area/update", method = RequestMethod.POST)
    public String updateAreaEntity (@ModelAttribute("areaDTO") AreaDTO areaDTO, @RequestParam Integer id) {
        areaServiceImpl.updateAreaEntity(areaDTO, id);
        return "redirect:/allArea";
    }
}
