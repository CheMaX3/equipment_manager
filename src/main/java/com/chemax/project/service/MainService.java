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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

    public SectionDTO createSectionEntity (SectionRequest request) {
        SectionEntity sectionEntity = buildSectionEntityFromRequest(request);
        sectionRepository.save(sectionEntity);
        return convertSectionEntityToDTO(sectionEntity);
    }

    public AreaDTO createAreaEntity(AreaRequest request) {
        SectionEntity sectionEntity = sectionRepository.getReferenceById(request.getSectionId());
        AreaEntity areaEntity = buildAreaEntityFromRequest(request, sectionEntity);
        areaRepository.save(areaEntity);
        return convertAreaEntityToDTO(areaEntity);
    }

    private SectionEntity getSectionEntity (Integer id) throws EntityNotFoundException {
        Optional<SectionEntity> returnedEntity = sectionRepository.findById(id);
        return returnedEntity.orElseThrow(EntityNotFoundException::new);
    }

    public SectionDTO getSectionDTO (Integer id) {
        return convertSectionEntityToDTO(getSectionEntity(id));
    }

    private AreaEntity getAreaEntity (Integer id) throws EntityNotFoundException {
        return areaRepository.getReferenceById(id);
    }

    public AreaDTO getAreaDTO (Integer id) {
        return convertAreaEntityToDTO(getAreaEntity(id));
    }

    public List<SectionDTO> getAllSectionDTOs() {
        List<SectionDTO> sectionDTOList = new ArrayList<>();
        for (SectionEntity s: sectionRepository.findAll()) {
            sectionDTOList.add(convertSectionEntityToDTO(s));
        }
        return sectionDTOList;
    }

    public List<AreaDTO> getAllAreaDTOs() {
        List<AreaDTO> areaDTOList = new ArrayList<>();
        for (AreaEntity a: areaRepository.findAll()) {
            areaDTOList.add(convertAreaEntityToDTO(a));
        }
        return areaDTOList;
    }

    public List<SectionDTO> getSectionDTOsByCount (Integer count) {
        List<SectionDTO> sectionDTOList = new ArrayList<>();
        for (SectionEntity s: sectionRepository.findAll()) {
            sectionDTOList.add(convertSectionEntityToDTO(s));
        }
        return sectionDTOList.stream().limit(count).collect(Collectors.toList());
    }

    public List<AreaDTO> getAreaDTOsByCount (Integer count) {
        List<AreaDTO> areaDTOList = new ArrayList<>();
        for (AreaEntity a: areaRepository.findAll()) {
            areaDTOList.add((convertAreaEntityToDTO(a)));
        }
        return areaDTOList.stream().limit(count).collect(Collectors.toList());
    }

    public void deleteSectionEntity (Integer id) {
        sectionRepository.delete(getSectionEntity(id));
    }

    public void deleteAreaEntity (Integer id) {
        areaRepository.delete(getAreaEntity(id));
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
                .orElse(entityToChange.getSectionConversationalName()));
        sectionRepository.save(entityToChange);
    }

    public void updateAreaEntity (AreaRequest request, Integer id) {
        AreaEntity areaEntity = areaRepository.getReferenceById(id);
        areaEntity.setAreaFullName(Optional.ofNullable(request.getAreaFullName()).orElse(areaEntity.getAreaFullName()));
        areaEntity.setAreaShortName(Optional.ofNullable(request.getAreaShortName()).orElse(areaEntity.getAreaShortName()));
        areaEntity.setAreaConversationalName(Optional.ofNullable(request.getAreaConversationalName()).orElse(areaEntity.getAreaConversationalName()));
        areaEntity.setSectionEntity(Optional.of(sectionRepository.getReferenceById(request.getSectionId())).orElse(areaEntity.getSectionEntity()));
        areaRepository.save(areaEntity);
    }

    private SectionEntity buildSectionEntityFromRequest (SectionRequest request) {
        SectionEntity builtSectionEntity = new SectionEntity();
        builtSectionEntity.setSectionFullName(request.getSectionFullName());
        builtSectionEntity.setSectionShortName(request.getSectionShortName());
        builtSectionEntity.setSectionConversationalName((request.getSectionConversationalName()));
        return builtSectionEntity;
    }

    private AreaEntity buildAreaEntityFromRequest(AreaRequest request, SectionEntity sectionEntity) {
        AreaEntity builtAreaEntity = new AreaEntity();
        builtAreaEntity.setAreaFullName(request.getAreaFullName());
        builtAreaEntity.setAreaShortName(request.getAreaShortName());
        builtAreaEntity.setAreaConversationalName(request.getAreaConversationalName());
        builtAreaEntity.setSectionEntity(sectionEntity);
        return builtAreaEntity;
    }

    private SectionDTO convertSectionEntityToDTO (SectionEntity sectionEntityToConvert) {
        SectionDTO sectionDTO = new SectionDTO();
        sectionDTO.setId(sectionEntityToConvert.getId());
        sectionDTO.setSectionFullName(sectionEntityToConvert.getSectionFullName());
        sectionDTO.setSectionShortName(sectionEntityToConvert.getSectionShortName());
        sectionDTO.setSectionConversationalName(sectionEntityToConvert.getSectionConversationalName());
        sectionDTO.setAreaDTOList(getAllAreaDTOs().stream().filter(areaDTO -> Objects.equals(areaDTO.getSectionID(), sectionEntityToConvert.getId()))
                .collect(Collectors.toList()));
        return sectionDTO;
    }

    private AreaDTO convertAreaEntityToDTO (AreaEntity areaEntity) {
        AreaDTO areaDTO = new AreaDTO();
        areaDTO.setId(areaEntity.getId());
        areaDTO.setAreaFullName(areaEntity.getAreaFullName());
        areaDTO.setAreaShortName(areaEntity.getAreaShortName());
        areaDTO.setAreaConversationalName(areaEntity.getAreaConversationalName());
        areaDTO.setSectionID(areaEntity.getSectionEntity().getId());
        return areaDTO;
    }
}
