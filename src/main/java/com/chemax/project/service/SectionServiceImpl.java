package com.chemax.project.service;

import com.chemax.project.dto.SectionDTO;
import com.chemax.project.entity.Section;
import com.chemax.project.exception.EntityNotFoundException;
import com.chemax.project.repository.SectionRepository;
import com.chemax.project.request.SectionCreateRequest;
import com.chemax.project.request.SectionUpdateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SectionServiceImpl implements SectionService {

    private final SectionRepository sectionRepository;
    private final AreaServiceImpl areaServiceImpl;

    public SectionServiceImpl(SectionRepository sectionRepository, AreaServiceImpl areaServiceImpl) {
        this.sectionRepository = sectionRepository;
        this.areaServiceImpl = areaServiceImpl;
    }

    public SectionDTO createSection(SectionCreateRequest sectionCreateRequest) {
        SectionDTO sectionDTO = new SectionDTO();
        try {
            Section section = sectionRepository.save(buildSectionFromRequest(sectionCreateRequest));
            sectionDTO = convertSectionToDTO(section);
        } catch (Exception ex) {
            log.error("Can't save object " + sectionCreateRequest.toString());
        }
        return sectionDTO;
    }

    public List<SectionDTO> getAllSectionDTOs() {
        List<SectionDTO> sectionDTOListFromDB = new ArrayList<>();
        try {
            sectionDTOListFromDB = sectionRepository.findAll().stream()
                    .map(this::convertSectionToDTO)
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            log.error("Can't retrieve objects from DB");
        }
        return sectionDTOListFromDB;
    }

    public boolean areaInclusionCheck(Integer sectionId) {
        boolean sectionIncludesAreas = false;
        try {
            sectionIncludesAreas = sectionRepository.getReferenceById(sectionId).getAreas().isEmpty();
        } catch (Exception ex) {
            log.error("Can't check area objects inclusion in section with id=" + sectionId);
        }
        return sectionIncludesAreas;
    }

    public void deleteSection(Integer sectionId) {
        try {
            sectionRepository.delete(sectionRepository.getReferenceById(sectionId));
        } catch (Exception ex) {
            log.error("Can't delete object with id=" + sectionId);
        }
    }

    public SectionDTO updateSection(SectionUpdateRequest sectionUpdateRequest) {
        SectionDTO sectionDTO = new SectionDTO();
        try {
            Section sectionFromDB = sectionRepository.findById(sectionUpdateRequest.getSectionId())
                    .orElseThrow(EntityNotFoundException::new);
            sectionFromDB.setSectionFullName(Optional.ofNullable(sectionUpdateRequest.getSectionFullName())
                    .orElse(sectionFromDB.getSectionFullName()));
            sectionFromDB.setSectionShortName(Optional.ofNullable(sectionUpdateRequest.getSectionShortName())
                    .orElse(sectionFromDB.getSectionShortName()));
            sectionFromDB.setSectionConversationalName(Optional.ofNullable(sectionUpdateRequest
                    .getSectionConversationalName()).orElse(sectionFromDB.getSectionConversationalName()));
            sectionRepository.save(sectionFromDB);
            sectionDTO = convertSectionToDTO(sectionFromDB);
        } catch (Exception ex) {
            log.error("Can't save object with id=" + sectionUpdateRequest.getSectionId());
        }
        return sectionDTO;
    }

    public SectionDTO getSectionDTOById(Integer sectionId) {
        SectionDTO sectionDTO = new SectionDTO();
        try {
            sectionDTO = convertSectionToDTO(sectionRepository.getReferenceById(sectionId));
        }
        catch (Exception ex) {
            log.error("Can't retrieve object from DB with id=" + sectionId);
        }
        return sectionDTO;
    }

    private Section buildSectionFromRequest(SectionCreateRequest sectionCreateRequest) {
        Section builtSection = new Section();
        builtSection.setSectionFullName(sectionCreateRequest.getSectionFullName());
        builtSection.setSectionShortName(sectionCreateRequest.getSectionShortName());
        builtSection.setSectionConversationalName(sectionCreateRequest.getSectionConversationalName());
        return builtSection;
    }

    private SectionDTO convertSectionToDTO(Section section) {
        SectionDTO sectionDTO = new SectionDTO();
        sectionDTO.setId(section.getId());
        sectionDTO.setSectionFullName(section.getSectionFullName());
        sectionDTO.setSectionShortName(section.getSectionShortName());
        sectionDTO.setSectionConversationalName(section.getSectionConversationalName());
        sectionDTO.setAreaDTOList(section.getAreas().stream()
                .map(areaServiceImpl::convertAreaToDTO)
                .collect(Collectors.toList()));
        return sectionDTO;
    }
}
