package com.chemax.project.service;

import com.chemax.project.dto.SectionDTO;
import com.chemax.project.request.SectionCreateRequest;
import com.chemax.project.request.SectionUpdateRequest;

import java.util.List;

public interface SectionService {

    void createSection(SectionCreateRequest sectionCreateRequest);

    SectionDTO getSectionDTO(Integer sectionId);

    List<SectionDTO> getAllSectionDTOs();

    boolean areaInclusionCheck(Integer sectionId);

    void deleteSection(Integer sectionId);

    void updateSection(SectionUpdateRequest sectionUpdateRequest);

}
