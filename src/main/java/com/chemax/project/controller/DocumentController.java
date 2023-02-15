package com.chemax.project.controller;

import com.chemax.project.service.DocumentService;
import org.bson.Document;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@Controller
public class DocumentController {
    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

//    @RequestMapping(value = "/addSection", method = RequestMethod.GET)
//    public String showAddSectionPage(Model model) {
//        SectionRequest sectionRequest = new SectionRequest();
//        model.addAttribute("sectionRequest", sectionRequest);
//        return "sectionRequestPage";
//    }

    @RequestMapping(value = "/addDocument", method = RequestMethod.POST)
    public String createDocument(@ModelAttribute("document") Document document) throws IOException {
        documentService.createDocument(document);
        return "redirect:/allEquipment";
    }

//    @GetMapping("/section/delete")
//    public String deleteSectionEntity(@RequestParam Integer id) {
//        service.deleteSectionEntity(id);
//        return "redirect:/allSection";
//    }
//
//    @RequestMapping(value = "/section/update", method = RequestMethod.GET)
//    public String showUpdateSectionPage (Model model, @RequestParam Integer id) {
//        SectionDTO sectionDTO = service.getSectionDTO(id);
//        model.addAttribute("sectionDTO", sectionDTO);
//        return "sectionUpdateRequestPage";
//    }
//
//    @RequestMapping(value = "/section/update", method = RequestMethod.POST)
//    public String updateSectionEntity (@ModelAttribute("sectionDTO") SectionDTO sectionDTO, @RequestParam Integer id) {
//        service.updateSectionEntity(sectionDTO, id);
//        return "redirect:/allSection";
//    }
}
