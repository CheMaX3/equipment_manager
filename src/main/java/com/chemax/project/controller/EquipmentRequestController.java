package com.chemax.project.controller;

import com.chemax.project.dto.EquipmentDTO;
import com.chemax.project.request.EquipmentRequest;
import com.chemax.project.service.EquipmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class EquipmentRequestController {
    private final EquipmentService service;

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
