package com.chemax.project.controller;

import com.chemax.project.service.DocumentService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@Controller
public class DocumentController {
    @Autowired
    private final DocumentService service;

    public DocumentController(DocumentService service) {
        this.service = service;
    }

    @RequestMapping(value = "/addDocument", method = RequestMethod.POST)
    public String createDocument(@ModelAttribute("document") Document document) throws IOException {
        service.createDocument(document);
        return "redirect:/allEquipment";
    }

}
