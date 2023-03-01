package com.chemax.project.service;

import com.chemax.project.entity.Document;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DocumentService {

    String addFile(MultipartFile upload, Integer equipmentId);

    void deleteFile(String id);

    Document downloadFile(String id);

    List<Document> getAllDocumentByEquipmentId(Integer equipmentId);

    List<Document> getAllDocument();

}
