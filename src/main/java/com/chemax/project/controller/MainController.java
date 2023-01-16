package com.chemax.project.controller;

import com.chemax.project.dto.AreaDTO;
import com.chemax.project.dto.SectionDTO;
import com.chemax.project.request.AreaRequest;
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

    @PostMapping("/addArea")
    public AreaDTO createAreaEntity (@RequestBody AreaRequest request) {
        return service.createAreaEntity(request);
    }

    @GetMapping("/section/{id}")
    public SectionDTO getSectionDTO (@PathVariable Integer id) {
        return service.getSectionDTO(id);
    }

    @GetMapping("/area/{id}")
    public AreaDTO getAreaDTO (@PathVariable Integer id) {return service.getAreaDTO(id);}

    @GetMapping("/delete/section/{id}")
    public void deleteSectionEntity (@PathVariable Integer id) {
        service.deleteSectionEntity(id);
    }

    @GetMapping("/delete/area/{id}")
    public void deleteAreaEntity (@PathVariable Integer id) {service.deleteAreaEntity(id);}

    @PutMapping ("/update/section/{id}")
    public void updateSectionEntity (@RequestBody SectionRequest request, @PathVariable Integer id) {
        service.updateSectionEntity(request, id);
    }

    @PutMapping ("/update/area/{id}")
    public AreaDTO updateAreaDTO (@RequestBody AreaRequest request, @PathVariable Integer id) {
        service.updateAreaEntity(request, id);
        return service.getAreaDTO(id);
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
