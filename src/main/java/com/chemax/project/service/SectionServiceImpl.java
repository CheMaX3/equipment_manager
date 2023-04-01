package com.chemax.project.service;

import com.chemax.project.dto.SectionDTO;
import com.chemax.project.entity.Section;
import com.chemax.project.exception.EntityNotFoundException;
import com.chemax.project.repository.SectionRepository;
import com.chemax.project.request.SectionCreateRequest;
import com.chemax.project.request.SectionUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SectionServiceImpl implements SectionService {

    private final SectionRepository sectionRepository;
    private final AreaServiceImpl areaServiceImpl;

    public SectionServiceImpl(SectionRepository sectionRepository, AreaServiceImpl areaServiceImpl) {
        this.sectionRepository = sectionRepository;
        this.areaServiceImpl = areaServiceImpl;
    }

    public void createSection(SectionCreateRequest sectionCreateRequest) {
        sectionRepository.save(buildSectionEntityFromRequest(sectionCreateRequest));
    }

    public SectionDTO getSectionDTO(Integer sectionId) {
        return convertSectionToDTO(sectionId);
    }
    //TODO:Подумать
    public List<SectionDTO> getAllSectionDTOs() {
        return sectionRepository.findAll().stream().map(section -> convertSectionToDTO(section.getId()))
                .collect(Collectors.toList());
    }

    public boolean areaInclusionCheck(Integer sectionId) {
        return sectionRepository.getReferenceById(sectionId).getAreas().isEmpty();
        }

    public void deleteSection(Integer sectionId) {
        sectionRepository.delete(sectionRepository.getReferenceById(sectionId));
    }
    //TODO:Подумать
    public void updateSection(SectionUpdateRequest sectionUpdateRequest) {
        Section sectionFromDB = sectionRepository.findById(sectionUpdateRequest.getSectionId())
                .orElseThrow(EntityNotFoundException::new);
        sectionFromDB.setSectionFullName(Optional.ofNullable(sectionUpdateRequest.getSectionFullName())
                .orElse(sectionFromDB.getSectionFullName()));
        sectionFromDB.setSectionShortName(Optional.ofNullable(sectionUpdateRequest.getSectionShortName())
                .orElse(sectionFromDB.getSectionShortName()));
        sectionFromDB.setSectionConversationalName(Optional.ofNullable(sectionUpdateRequest
                .getSectionConversationalName()).orElse(sectionFromDB.getSectionConversationalName()));
        sectionRepository.save(sectionFromDB);
    }

    private Section buildSectionEntityFromRequest(SectionCreateRequest sectionCreateRequest) {
        Section builtSection = new Section();
        builtSection.setSectionFullName(sectionCreateRequest.getSectionFullName());
        builtSection.setSectionShortName(sectionCreateRequest.getSectionShortName());
        builtSection.setSectionConversationalName(sectionCreateRequest.getSectionConversationalName());
        return builtSection;
    }

    private SectionDTO convertSectionToDTO(Integer sectionForConversionId) {
        SectionDTO sectionDTO = new SectionDTO();
        Section sectionFromDB = getSection(sectionForConversionId);
        sectionDTO.setId(sectionFromDB.getId());
        sectionDTO.setSectionFullName(sectionFromDB.getSectionFullName());
        sectionDTO.setSectionShortName(sectionFromDB.getSectionShortName());
        sectionDTO.setSectionConversationalName(sectionFromDB.getSectionConversationalName());
        //TODO:Подумать
        sectionDTO.setAreaDTOList(sectionFromDB.getAreas().stream()
                .map(areaServiceImpl::convertAreaEntityToDTO)
                .collect(Collectors.toList()));
        return sectionDTO;
    }

    private Section getSection(Integer sectionId) {
        return sectionRepository.findById(sectionId).orElseThrow(EntityNotFoundException::new);
    }
}
