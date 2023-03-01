package com.chemax.project.service;

import com.chemax.project.dto.SectionDTO;
import com.chemax.project.entity.Section;
import com.chemax.project.exception.EntityNotFoundException;
import com.chemax.project.repository.SectionRepository;
import com.chemax.project.request.SectionRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

    public SectionDTO createSectionEntity(SectionRequest request) {
        Section section = buildSectionEntityFromRequest(request);
        sectionRepository.save(section);
        return convertSectionEntityToDTO(section);
    }

    private Section getSectionEntity(Integer id) throws EntityNotFoundException {
        Optional<Section> returnedEntity = sectionRepository.findById(id);
        return returnedEntity.orElseThrow(EntityNotFoundException::new);
    }

    public SectionDTO getSectionDTO(Integer id) {
        return convertSectionEntityToDTO(getSectionEntity(id));
    }

    public List<SectionDTO> getAllSectionDTOs() {
        List<SectionDTO> sectionDTOList = new ArrayList<>();
        for (Section s : sectionRepository.findAll()) {
            sectionDTOList.add(convertSectionEntityToDTO(s));
        }
        return sectionDTOList;
    }

    public List<SectionDTO> getSectionDTOsByCount(Integer count) {
        List<SectionDTO> sectionDTOList = new ArrayList<>();
        for (Section s : sectionRepository.findAll()) {
            sectionDTOList.add(convertSectionEntityToDTO(s));
        }
        return sectionDTOList.stream().limit(count).collect(Collectors.toList());
    }

    public boolean deleteSectionEntity(Integer id) {
        Section sectionToDelete = sectionRepository.getReferenceById(id);
        if (sectionToDelete.getAreaEntities().isEmpty()) {
            sectionRepository.delete(getSectionEntity(id));
            return true;
        } else {
            return false;
        }
    }

    public void updateSectionEntity(SectionDTO sectionDTO, Integer id) {
        Optional<Section> returnedEntity = sectionRepository.findById(id);
        Section entityToChange = returnedEntity.orElseThrow(EntityNotFoundException::new);
        if (sectionDTO.getSectionFullName() != null) {
            entityToChange.setSectionFullName(sectionDTO.getSectionFullName());
        }
        if (sectionDTO.getSectionShortName() != null) {
            entityToChange.setSectionShortName(sectionDTO.getSectionShortName());
        }
        entityToChange.setSectionConversationalName(Optional.ofNullable(sectionDTO.getSectionConversationalName())
                .orElse(entityToChange.getSectionConversationalName()));
        sectionRepository.save(entityToChange);
    }

    private Section buildSectionEntityFromRequest(SectionRequest request) {
        Section builtSection = new Section();
        builtSection.setSectionFullName(request.getSectionFullName());
        builtSection.setSectionShortName(request.getSectionShortName());
        builtSection.setSectionConversationalName((request.getSectionConversationalName()));
        return builtSection;
    }

    public SectionDTO convertSectionEntityToDTO(Section sectionToConvert) {
        SectionDTO sectionDTO = new SectionDTO();
        sectionDTO.setId(sectionToConvert.getId());
        sectionDTO.setSectionFullName(sectionToConvert.getSectionFullName());
        sectionDTO.setSectionShortName(sectionToConvert.getSectionShortName());
        sectionDTO.setSectionConversationalName(sectionToConvert.getSectionConversationalName());
        sectionDTO.setAreaDTOList(areaServiceImpl.getAllAreaDTOs().stream().filter(areaDTO -> Objects.equals(areaDTO.getSectionId(), sectionToConvert.getId()))
                .collect(Collectors.toList()));
        return sectionDTO;
    }

}
