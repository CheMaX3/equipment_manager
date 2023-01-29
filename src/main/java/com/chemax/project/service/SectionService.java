package com.chemax.project.service;

import com.chemax.project.dto.SectionDTO;
import com.chemax.project.entities.SectionEntity;
import com.chemax.project.exceptions.EntityNotFoundException;
import com.chemax.project.repository.SectionEntityRepository;
import com.chemax.project.request.SectionRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SectionService {
    private final SectionEntityRepository sectionRepository;
    private final AreaService areaService;

    public SectionService(SectionEntityRepository sectionRepository, AreaService areaService) {
        this.sectionRepository = sectionRepository;
        this.areaService = areaService;
    }

    public SectionDTO createSectionEntity(SectionRequest request) {
        SectionEntity sectionEntity = buildSectionEntityFromRequest(request);
        sectionRepository.save(sectionEntity);
        return convertSectionEntityToDTO(sectionEntity);
    }

    private SectionEntity getSectionEntity(Integer id) throws EntityNotFoundException {
        Optional<SectionEntity> returnedEntity = sectionRepository.findById(id);
        return returnedEntity.orElseThrow(EntityNotFoundException::new);
    }

    public SectionDTO getSectionDTO(Integer id) {
        return convertSectionEntityToDTO(getSectionEntity(id));
    }

    public List<SectionDTO> getAllSectionDTOs() {
        List<SectionDTO> sectionDTOList = new ArrayList<>();
        for (SectionEntity s : sectionRepository.findAll()) {
            sectionDTOList.add(convertSectionEntityToDTO(s));
        }
        return sectionDTOList;
    }

    public List<SectionDTO> getSectionDTOsByCount(Integer count) {
        List<SectionDTO> sectionDTOList = new ArrayList<>();
        for (SectionEntity s : sectionRepository.findAll()) {
            sectionDTOList.add(convertSectionEntityToDTO(s));
        }
        return sectionDTOList.stream().limit(count).collect(Collectors.toList());
    }

    public void deleteSectionEntity(Integer id) {
        sectionRepository.delete(getSectionEntity(id));
    }

    public void updateSectionEntity(SectionRequest request, Integer id) {
        Optional<SectionEntity> returnedEntity = sectionRepository.findById(id);
        SectionEntity entityToChange = returnedEntity.orElseThrow(EntityNotFoundException::new);
        if (request.getSectionFullName() != null) {
            entityToChange.setSectionFullName(request.getSectionFullName());
        }
        if (request.getSectionShortName() != null) {
            entityToChange.setSectionShortName(request.getSectionShortName());
        }
        entityToChange.setSectionConversationalName(Optional.ofNullable(request.getSectionConversationalName())
                .orElse(entityToChange.getSectionConversationalName()));
        sectionRepository.save(entityToChange);
    }

    private SectionEntity buildSectionEntityFromRequest(SectionRequest request) {
        SectionEntity builtSectionEntity = new SectionEntity();
        builtSectionEntity.setSectionFullName(request.getSectionFullName());
        builtSectionEntity.setSectionShortName(request.getSectionShortName());
        builtSectionEntity.setSectionConversationalName((request.getSectionConversationalName()));
        return builtSectionEntity;
    }

    public SectionDTO convertSectionEntityToDTO(SectionEntity sectionEntityToConvert) {
        SectionDTO sectionDTO = new SectionDTO();
        sectionDTO.setId(sectionEntityToConvert.getId());
        sectionDTO.setSectionFullName(sectionEntityToConvert.getSectionFullName());
        sectionDTO.setSectionShortName(sectionEntityToConvert.getSectionShortName());
        sectionDTO.setSectionConversationalName(sectionEntityToConvert.getSectionConversationalName());
        sectionDTO.setAreaDTOList(areaService.getAllAreaDTOs().stream().filter(areaDTO -> Objects.equals(areaDTO.getSectionID(), sectionEntityToConvert.getId()))
                .collect(Collectors.toList()));
        return sectionDTO;
    }

}
