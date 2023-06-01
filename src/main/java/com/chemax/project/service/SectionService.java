package com.chemax.project.service;

import com.chemax.project.dto.SectionDTO;
import com.chemax.project.request.SectionCreateRequest;
import com.chemax.project.request.SectionUpdateRequest;

import java.util.List;

public interface SectionService {

    SectionDTO createSection(SectionCreateRequest sectionCreateRequest);

    List<SectionDTO> getAllSectionDTOs();

    void deleteSection(Integer sectionId);

    SectionDTO updateSection(SectionUpdateRequest sectionUpdateRequest);

    SectionDTO getSectionDTOById(Integer sectionId);
}
