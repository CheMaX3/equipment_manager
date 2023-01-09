package com.chemax.project.service;

import com.chemax.project.dto.TestDTO;
import com.chemax.project.exceptions.EntityNotFoundException;
import com.chemax.project.repository.TestRepository;
import com.chemax.project.request.SectionEntity;
import com.chemax.project.request.SectionRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TestService {
    private final TestRepository repository;

    public TestService (TestRepository repository) {
        this.repository = repository;
    }

    public SectionEntity createSectionEntity (SectionRequest request) {
        SectionEntity sectionEntity = buildSectionEntityFromRequest(request);
        return repository.save(sectionEntity);
    }

    public SectionEntity getSectionEntity (Integer id) throws EntityNotFoundException {
        Optional<SectionEntity> returnedEntity = repository.findById(id);
        return returnedEntity.orElseThrow(EntityNotFoundException::new);
    }

    public List<SectionEntity> getAllSectionEntities() {
        return repository.findAll();
    }

    public List<SectionEntity> getSectionEntitiesByCount (Integer count) {
        return repository.findAll().stream().limit(count).collect(Collectors.toList());
    }

    public void deleteSectionEntity (Integer id) {
        repository.delete(getSectionEntity(id));
    }


    public void updateSectionEntity (SectionRequest request, Integer id) {
        Optional<SectionEntity> returnedEntity = repository.findById(id);
        SectionEntity entityToChange = returnedEntity.orElseThrow(EntityNotFoundException::new);
        if (request.getSectionFullName() != null) {
            entityToChange.setSectionFullName(request.getSectionFullName());
        }
        if (request.getSectionShortName() != null) {
            entityToChange.setSectionShortName(request.getSectionShortName());
        }
        entityToChange.setSectionConversationalName(Optional.ofNullable(request.getSectionConversationalName())
                .orElse(request.getSectionConversationalName()));
        repository.save(entityToChange);
    }

    private SectionEntity buildSectionEntityFromRequest (SectionRequest request) {
        SectionEntity builtSectionEntity = new SectionEntity();
        builtSectionEntity.setSectionFullName(request.getSectionFullName());
        builtSectionEntity.setSectionShortName(request.getSectionShortName());
        builtSectionEntity.setSectionConversationalName((request.getSectionConversationalName()));
        return builtSectionEntity;
    }

    public TestDTO getTestDTO (Integer id) {
        return convertEntityToDTO(getSectionEntity(id));
    }

    private TestDTO convertEntityToDTO (SectionEntity entityToConvert) {
        TestDTO testDTO = new TestDTO();
        testDTO.setId(entityToConvert.getId());
        testDTO.setSectionFullName(entityToConvert.getSectionFullName());
        testDTO.setSectionShortName(entityToConvert.getSectionShortName());
        testDTO.setSectionConversationalName(entityToConvert.getSectionConversationalName());
        return testDTO;
    }

    private SectionEntity convertDTOToEntity (TestDTO dtoToConvert) {
        SectionEntity sectionEntity = new SectionEntity();
        sectionEntity.setSectionFullName(dtoToConvert.getSectionFullName());
        sectionEntity.setSectionShortName(dtoToConvert.getSectionShortName());
        sectionEntity.setSectionConversationalName(dtoToConvert.getSectionConversationalName());
        return sectionEntity;
    }
}
