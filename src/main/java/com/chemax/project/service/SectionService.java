package com.chemax.project.service;

import com.chemax.project.dto.SectionDTO;
import com.chemax.project.entity.Section;
import com.chemax.project.request.SectionRequest;

import java.util.List;

public interface SectionService {

    SectionDTO createSectionEntity(SectionRequest request);

    SectionDTO getSectionDTO(Integer id);

    List<SectionDTO> getAllSectionDTOs();

    List<SectionDTO> getSectionDTOsByCount(Integer count);

    boolean deleteSectionEntity(Integer id);

    void updateSectionEntity(SectionDTO sectionDTO, Integer id);

    SectionDTO convertSectionEntityToDTO(Section sectionToConvert);

}
