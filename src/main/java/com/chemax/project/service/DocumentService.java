package com.chemax.project.service;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.Binary;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class DocumentService {
    private final MongoClient mongo;

    public DocumentService(MongoClient mongo) {
        this.mongo = mongo;
    }

    public void createDocument(Document document) throws IOException {
        InputStream stream = new FileInputStream(String.valueOf(document));
        byte [] fileBytes = stream.readAllBytes();
        System.out.println("File size: " + fileBytes.length);
        Binary binData = new Binary(fileBytes);
        Document doc = new Document("_id", 1).append("file", binData);
        MongoDatabase db = mongo.getDatabase("files");
        MongoCollection<Document> files = db.getCollection("passports");
        files.insertOne(doc);
    }

//    private Document getDocument(String name) throws EntityNotFoundException {
//        return documentRepository.findByName(name);
//    }

//    public SectionDTO getSectionDTO(Integer id) {
//        return convertSectionEntityToDTO(getSectionEntity(id));
//    }
//
//    public List<SectionDTO> getAllSectionDTOs() {
//        List<SectionDTO> sectionDTOList = new ArrayList<>();
//        for (SectionEntity s : sectionRepository.findAll()) {
//            sectionDTOList.add(convertSectionEntityToDTO(s));
//        }
//        return sectionDTOList;
//    }
//
//    public List<SectionDTO> getSectionDTOsByCount(Integer count) {
//        List<SectionDTO> sectionDTOList = new ArrayList<>();
//        for (SectionEntity s : sectionRepository.findAll()) {
//            sectionDTOList.add(convertSectionEntityToDTO(s));
//        }
//        return sectionDTOList.stream().limit(count).collect(Collectors.toList());
//    }

//    public void deleteSectionEntity(Integer id) {
//        SectionEntity sectionEntityToDelete = sectionRepository.getReferenceById(id);
//        if (sectionEntityToDelete.getAreaEntities().isEmpty()) {
//            sectionRepository.delete(getSectionEntity(id));
//        } else {
//            throw new NeedToMoveEntityException();
//        }
//    }
//
//    public void updateSectionEntity(SectionDTO sectionDTO, Integer id) {
//        Optional<SectionEntity> returnedEntity = sectionRepository.findById(id);
//        SectionEntity entityToChange = returnedEntity.orElseThrow(EntityNotFoundException::new);
//        if (sectionDTO.getSectionFullName() != null) {
//            entityToChange.setSectionFullName(sectionDTO.getSectionFullName());
//        }
//        if (sectionDTO.getSectionShortName() != null) {
//            entityToChange.setSectionShortName(sectionDTO.getSectionShortName());
//        }
//        entityToChange.setSectionConversationalName(Optional.ofNullable(sectionDTO.getSectionConversationalName())
//                .orElse(entityToChange.getSectionConversationalName()));
//        sectionRepository.save(entityToChange);
//    }
//
//    private SectionEntity buildSectionEntityFromRequest(SectionRequest request) {
//        SectionEntity builtSectionEntity = new SectionEntity();
//        builtSectionEntity.setSectionFullName(request.getSectionFullName());
//        builtSectionEntity.setSectionShortName(request.getSectionShortName());
//        builtSectionEntity.setSectionConversationalName((request.getSectionConversationalName()));
//        return builtSectionEntity;
//    }
//
//    public SectionDTO convertSectionEntityToDTO(SectionEntity sectionEntityToConvert) {
//        SectionDTO sectionDTO = new SectionDTO();
//        sectionDTO.setId(sectionEntityToConvert.getId());
//        sectionDTO.setSectionFullName(sectionEntityToConvert.getSectionFullName());
//        sectionDTO.setSectionShortName(sectionEntityToConvert.getSectionShortName());
//        sectionDTO.setSectionConversationalName(sectionEntityToConvert.getSectionConversationalName());
//        sectionDTO.setAreaDTOList(areaService.getAllAreaDTOs().stream().filter(areaDTO -> Objects.equals(areaDTO.getSectionId(), sectionEntityToConvert.getId()))
//                .collect(Collectors.toList()));
//        return sectionDTO;
//    }

}
