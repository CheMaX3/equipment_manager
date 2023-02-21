package com.chemax.project.service;

import com.chemax.project.entities.Document;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.apache.poi.util.IOUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DocumentService {

    private final GridFsTemplate template;
    private final GridFsOperations operations;

    public DocumentService(GridFsTemplate template, GridFsOperations operations) {
        this.template = template;
        this.operations = operations;
    }

    public String addFile(MultipartFile upload, Integer equipmentId) {

        try {
            DBObject metadata = new BasicDBObject();
            metadata.put("fileSize", upload.getSize());
            metadata.put("equipmentId", equipmentId);
            Object fileID = template.store(upload.getInputStream(), upload.getOriginalFilename(), upload.getContentType(), metadata);
            return fileID.toString();
        } catch (IOException IO) {
            throw new RuntimeException("Error loading file");
        }
    }

    public void deleteFile(String id) {
        template.delete(new Query(Criteria.where("_id").is(id)));
    }

    public Document downloadFile(String id) {

        try {
            GridFSFile gridFSFile = template.findOne(new Query(Criteria.where("_id").is(id)));
            Document document = new Document();

            if (gridFSFile != null && gridFSFile.getMetadata() != null) {
                document.setName(gridFSFile.getFilename());
                document.setType(gridFSFile.getMetadata().get("_contentType").toString());
                document.setSize(gridFSFile.getMetadata().get("fileSize").toString());
                document.setContent(IOUtils.toByteArray(operations.getResource(gridFSFile).getInputStream()));
            }
            return document;
        } catch (IOException IO) {
            throw new RuntimeException("Error downloading file");
        }
    }

    public List<Document> getAllDocumentByEquipmentId(Integer equipmentId) {

        try {
            List<GridFSFile> gridFSFileList = new ArrayList<GridFSFile>();
            template.find(new Query(Criteria.where("metadata.equipmentId").is(equipmentId))).into(gridFSFileList);
            List<Document> documentList = new ArrayList<Document>();
            for (GridFSFile file : gridFSFileList) {
                if (file != null && file.getMetadata() != null) {
                    Document document = new Document();
                    document.setId(file.getObjectId().toHexString());
                    document.setName(file.getFilename());
                    document.setType(file.getMetadata().get("_contentType").toString());
                    document.setSize(file.getMetadata().get("fileSize").toString());
                    document.setContent(IOUtils.toByteArray(operations.getResource(file).getInputStream()));
                    documentList.add(document);
                }
            }
            return documentList;
        } catch (IOException IO) {
            throw new RuntimeException("Error loading files list");
        }
    }

        public List<Document> getAllDocument() {

            try {
                List<GridFSFile> gridFSFileList = new ArrayList<GridFSFile>();
                template.find(new Query()).into(gridFSFileList);
                List<Document> documentList = new ArrayList<Document>();
                for (GridFSFile file : gridFSFileList) {
                    if (file != null && file.getMetadata() != null) {
                        Document document = new Document();
                        document.setId(file.getId().toString());
                        document.setName(file.getFilename());
                        document.setType(file.getMetadata().get("_contentType").toString());
                        document.setSize(file.getMetadata().get("fileSize").toString());
                        document.setContent(IOUtils.toByteArray(operations.getResource(file).getInputStream()));
                        documentList.add(document);
                    }
                }
                return documentList;
            } catch (IOException IO) {
                throw new RuntimeException("Error loading files list");
            }
    }
}
