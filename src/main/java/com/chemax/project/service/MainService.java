package com.chemax.project.service;

import com.chemax.project.dto.AreaDTO;
import com.chemax.project.dto.SectionDTO;
import com.chemax.project.entities.AreaEntity;
import com.chemax.project.exceptions.EntityNotFoundException;
import com.chemax.project.repository.AreaEntityRepository;
import com.chemax.project.repository.SectionEntityRepository;
import com.chemax.project.entities.SectionEntity;
import com.chemax.project.request.AreaRequest;
import com.chemax.project.request.SectionRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MainService {
    private final SectionEntityRepository sectionRepository;
    private final AreaEntityRepository areaRepository;

    public MainService(SectionEntityRepository sectionRepository, AreaEntityRepository areaRepository) {
        this.sectionRepository = sectionRepository;
        this.areaRepository = areaRepository;
    }

    public SectionEntity createSectionEntity (SectionRequest request) {
        SectionEntity sectionEntity = buildSectionEntityFromRequest(request);
        return sectionRepository.save(sectionEntity);
    }

    public AreaEntity createAreaEntity(AreaRequest request) {
        SectionEntity sectionEntity = sectionRepository.getReferenceById(request.getSectionId());
        AreaEntity areaEntity = buildAreaEntityFromRequest(request, sectionEntity);
        return areaRepository.save(areaEntity);
    }

    public SectionEntity getSectionEntity (Integer id) throws EntityNotFoundException {
        Optional<SectionEntity> returnedEntity = sectionRepository.findById(id);
        return returnedEntity.orElseThrow(EntityNotFoundException::new);
    }

    public AreaDTO getAreaDTO (Integer id) throws EntityNotFoundException {
        try {
            return convertAreaEntityToDTO(areaRepository.getReferenceById(id));
        } catch (EntityNotFoundException nfe) {
            throw nfe;
        }
    }

    public List<SectionEntity> getAllSectionEntities() {
        return sectionRepository.findAll();
    }

    public List<SectionEntity> getSectionEntitiesByCount (Integer count) {
        return sectionRepository.findAll().stream().limit(count).collect(Collectors.toList());
    }

    public void deleteSectionEntity (Integer id) {
        sectionRepository.delete(getSectionEntity(id));
    }


    public void updateSectionEntity (SectionRequest request, Integer id) {
        Optional<SectionEntity> returnedEntity = sectionRepository.findById(id);
        SectionEntity entityToChange = returnedEntity.orElseThrow(EntityNotFoundException::new);
        if (request.getSectionFullName() != null) {
            entityToChange.setSectionFullName(request.getSectionFullName());
        }
        if (request.getSectionShortName() != null) {
            entityToChange.setSectionShortName(request.getSectionShortName());
        }
        entityToChange.setSectionConversationalName(Optional.ofNullable(request.getSectionConversationalName())
                .orElse(request.getSectionConversationalName()));
        sectionRepository.save(entityToChange);
    }

    private AreaEntity buildAreaEntityFromRequest(AreaRequest request, SectionEntity sectionEntity) {
        AreaEntity builtAreaEntity = new AreaEntity();
        builtAreaEntity.setAreaFullName(request.getAreaFullName());
        builtAreaEntity.setAreaShortName(request.getAreaShortName());
        builtAreaEntity.setAreaConversationalName(request.getAreaConversationalName());
        builtAreaEntity.setSectionEntity(sectionEntity);
        return builtAreaEntity;
    }

    private SectionEntity buildSectionEntityFromRequest (SectionRequest request) {
        SectionEntity builtSectionEntity = new SectionEntity();
        builtSectionEntity.setSectionFullName(request.getSectionFullName());
        builtSectionEntity.setSectionShortName(request.getSectionShortName());
        builtSectionEntity.setSectionConversationalName((request.getSectionConversationalName()));
        return builtSectionEntity;
    }

    public SectionDTO getSectionEntityDTO (Integer id) {
        return convertEntityToDTO(getSectionEntity(id));
    }

    private SectionDTO convertEntityToDTO (SectionEntity entityToConvert) {
        SectionDTO sectionDTO = new SectionDTO();
        sectionDTO.setId(entityToConvert.getId());
        sectionDTO.setSectionFullName(entityToConvert.getSectionFullName());
        sectionDTO.setSectionShortName(entityToConvert.getSectionShortName());
        sectionDTO.setSectionConversationalName(entityToConvert.getSectionConversationalName());
        return sectionDTO;
    }

    private AreaDTO convertAreaEntityToDTO (AreaEntity areaEntity) {
        AreaDTO areaDTO = new AreaDTO();
        areaDTO.setAreaFullName(areaEntity.getAreaFullName());
        areaDTO.setAreaShortName(areaEntity.getAreaShortName());
        areaDTO.setAreaConversationalName(areaDTO.getAreaConversationalName());
        return areaDTO;
    }

    private SectionEntity convertDTOToEntity (SectionDTO dtoToConvert) {
        SectionEntity sectionEntity = new SectionEntity();
        sectionEntity.setSectionFullName(dtoToConvert.getSectionFullName());
        sectionEntity.setSectionShortName(dtoToConvert.getSectionShortName());
        sectionEntity.setSectionConversationalName(dtoToConvert.getSectionConversationalName());
        return sectionEntity;
    }
}
