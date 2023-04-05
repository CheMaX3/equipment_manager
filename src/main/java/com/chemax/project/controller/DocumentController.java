package com.chemax.project.controller;

import com.chemax.project.dto.EquipmentDTO;
import com.chemax.project.entity.Document;
import com.chemax.project.service.DocumentServiceImpl;
import com.chemax.project.service.EquipmentServiceImpl;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class DocumentController {

    private final DocumentServiceImpl documentServiceImpl;
    private final EquipmentServiceImpl equipmentServiceImpl;

    public DocumentController(DocumentServiceImpl documentServiceImpl, EquipmentServiceImpl equipmentServiceImpl) {
        this.documentServiceImpl = documentServiceImpl;
        this.equipmentServiceImpl = equipmentServiceImpl;
    }

    @RequestMapping(value = "/equipment/addDocument", method = RequestMethod.GET)
    public String showAddDocumentPage(Model model, @RequestParam Integer id) {
        EquipmentDTO equipmentDTO = equipmentServiceImpl.getEquipmentDTO(id);
        Document document = new Document();
        model.addAttribute("equipmentDTO", equipmentDTO);
        model.addAttribute("document", document);
        return "documentRequestPage";
    }

    @RequestMapping(value = "/equipment/addDocument", method = RequestMethod.POST)
    public String addFile(@RequestParam("file") MultipartFile file, Model model, @RequestParam Integer id) {
        EquipmentDTO equipmentDTO = equipmentServiceImpl.getEquipmentDTO(id);
        String fileId = documentServiceImpl.addFile(file, equipmentDTO.getId());
        return "redirect:/equipment/showDocumentStorage?id=" + equipmentDTO.getId();
    }

    @RequestMapping(value = "/equipment/showDocumentStorage", method = RequestMethod.GET)
    public String showFilesPage(Model model, @RequestParam Integer id) {
        EquipmentDTO equipmentDTO = equipmentServiceImpl.getEquipmentDTO(id);
        List<Document> documentList = documentServiceImpl.getAllDocumentByEquipmentId(id);
        model.addAttribute("equipmentDTO", equipmentDTO);
        model.addAttribute("documentList", documentList);
        return "filesPage";
    }

    @RequestMapping(value = "/files/delete", method = RequestMethod.GET)
    public String deleteFile(@RequestParam String id) {
        documentServiceImpl.deleteFile(id);
        return "redirect:/allEquipment";
    }

    @RequestMapping(value = "/files/", method = RequestMethod.GET)
    public ResponseEntity<ByteArrayResource> downloadDoc(@RequestParam String id) {
        Document document = documentServiceImpl.downloadFile(id);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(document.getType() ))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + document.getName() + "\"")
                .body(new ByteArrayResource(document.getContent()));
    }
}
