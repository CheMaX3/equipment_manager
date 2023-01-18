package com.chemax.project.controller;

import com.chemax.project.dto.AreaDTO;
import com.chemax.project.dto.EquipmentDTO;
import com.chemax.project.dto.EquipmentTypeDTO;
import com.chemax.project.dto.SectionDTO;
import com.chemax.project.entities.EquipmentEntity;
import com.chemax.project.request.AreaRequest;
import com.chemax.project.request.EquipmentRequest;
import com.chemax.project.request.EquipmentTypeRequest;
import com.chemax.project.request.SectionRequest;
import com.chemax.project.service.MainService;
import org.springframework.web.bind.annotation.*;


@RestController
public class MainController {
    private final MainService service;

    public MainController(MainService service) {
        this.service = service;
    }

    @PostMapping("/addSection")
    public SectionDTO createSectionEntity (@RequestBody SectionRequest request) {
        return service.createSectionEntity(request);
    }

    @GetMapping("/section/{id}")
    public SectionDTO getSectionDTO (@PathVariable Integer id) {
        return service.getSectionDTO(id);
    }

    @GetMapping("/section/delete/{id}")
    public void deleteSectionEntity (@PathVariable Integer id) {
        service.deleteSectionEntity(id);
    }

    @PutMapping ("/section/update/{id}")
    public SectionDTO updateSectionEntity (@RequestBody SectionRequest request, @PathVariable Integer id) {
        service.updateSectionEntity(request, id);
        return getSectionDTO(id);
    }

    @PostMapping("/addArea")
    public AreaDTO createAreaEntity (@RequestBody AreaRequest request) {
        return service.createAreaEntity(request);
    }

    @GetMapping("/area/{id}")
    public AreaDTO getAreaDTO (@PathVariable Integer id) {return service.getAreaDTO(id);}

    @GetMapping("/area/delete/{id}")
    public void deleteAreaEntity (@PathVariable Integer id) {service.deleteAreaEntity(id);}

    @PutMapping ("/area/update/{id}")
    public AreaDTO updateAreaEntity (@RequestBody AreaRequest request, @PathVariable Integer id) {
        service.updateAreaEntity(request, id);
        return getAreaDTO(id);
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

/*    @GetMapping("/webmorda/addSection")
    public String webmordaAddSetion() {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <form id=\"собака\" action=\"\">\n" +
                "        <label>\n" +
                "            sectionFullName\n" +
                "            <input type=\"text\" name=\"sectionFullName\" />\n" +
                "        </label>\n" +
                "        <br />\n" +
                "        <label>\n" +
                "            sectionShortName\n" +
                "            <input type=\"text\" name=\"sectionShortName\" />\n" +
                "        </label>\n" +
                "        <br />\n" +
                "        <label>\n" +
                "            sectionConversationalName\n" +
                "            <input type=\"text\" name=\"sectionConversationalName\" />\n" +
                "        </label>\n" +
                "        <br />\n" +
                "        <input type=\"submit\" value=\"засобачить\">\n" +
                "    </form>\n" +
                "    <div id=\"#response\"></div>\n" +
                "<script>\n" +
                "    console.log(2, document.querySelector(\"#response\"))\n" +
                "    document.querySelector(\"#собака\").addEventListener(\"submit\", async function (event) {\n" +
                "        event.preventDefault();\n" +
                "\n" +
                "        const body = {\n" +
                "            sectionFullName: this.querySelector('[name=\"sectionFullName\"]').value,\n" +
                "            sectionShortName: this.querySelector('[name=\"sectionShortName\"]').value,\n" +
                "            sectionConversationalName: this.querySelector('[name=\"sectionConversationalName\"]').value,\n" +
                "        };\n" +
                "        console.log('body')\n" +
                "\n" +
                "        const rawResponse = await fetch('http://localhost:8080/addSection', {\n" +
                "            method: 'POST',\n" +
                "            headers: {\n" +
                "                'Accept': 'application/json',\n" +
                "                'Content-Type': 'application/json'\n" +
                "            },\n" +
                "            body: JSON.stringify(body)\n" +
                "        });\n" +
                "\n" +
                "        alert(JSON.stringify(await rawResponse.json(), null, 2))\n" +
                "    });\n" +
                "</script>\n" +
                "</body>\n" +
                "</html>\n";
    }*/
}
