package com.chemax.project.service;

import com.chemax.project.dto.SectionDTO;
import com.chemax.project.repository.SectionRepository;
import com.chemax.project.request.SectionCreateRequest;
import com.chemax.project.request.SectionUpdateRequest;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class SectionServiceImplTest {
    @Autowired
    private SectionService sectionService;
    @Autowired
    private SectionRepository repository;
    private SectionDTO sectionDTO;

    @BeforeEach
    public void createStubSection() {
        sectionDTO = sectionService.createSection(buildSectionRequest());
    }

    @AfterEach
    public void clearDataBase() {
        repository.deleteAll();
    }

    @Test
    void createSectionTest() {
        SectionCreateRequest sectionCreateRequest = buildSectionRequest();
        SectionDTO sectionDTO = sectionService.createSection(sectionCreateRequest);
        Assertions.assertNotNull(sectionDTO.getId());
        Assertions.assertEquals(sectionDTO.getSectionFullName(), sectionCreateRequest.getSectionFullName());
        Assertions.assertEquals(sectionDTO.getSectionShortName(), sectionCreateRequest.getSectionShortName());
        Assertions.assertEquals(sectionDTO.getSectionConversationalName(),
                sectionCreateRequest.getSectionConversationalName());
    }

    @Test
    void getAllSectionDTOsTest() {
        List<SectionDTO> actualList = sectionService.getAllSectionDTOs();
        Assertions.assertEquals(1, actualList.size());
    }

    @Test
    void deleteSectionTest() {
        sectionService.deleteSection(1);
        SectionDTO actualDTO = sectionService.getSectionDTOById(1);
        Assertions.assertNull(actualDTO.getId());
        Assertions.assertNull(actualDTO.getSectionFullName());
        Assertions.assertNull(actualDTO.getSectionShortName());
        Assertions.assertNull(actualDTO.getSectionConversationalName());
    }

    @Test
    void updateSectionTest() {
        SectionUpdateRequest request = buildUpdateSectionRequest();
        SectionDTO updatedDTO = sectionService.updateSection(request);
        Assertions.assertEquals(request.getSectionId(), updatedDTO.getId());
        Assertions.assertEquals(request.getSectionFullName(),
                updatedDTO.getSectionFullName());
        Assertions.assertEquals(request.getSectionShortName(),
                updatedDTO.getSectionShortName());
        Assertions.assertEquals(request.getSectionConversationalName(),
                updatedDTO.getSectionConversationalName());
    }

    @Test
    void getSectionDTOById() {
        SectionDTO actualSectionDTO = sectionService.getSectionDTOById(1);
        Assertions.assertEquals(buildSectionRequest().getSectionFullName(), actualSectionDTO.getSectionFullName());
        Assertions.assertEquals(buildSectionRequest().getSectionShortName(),
                actualSectionDTO.getSectionShortName());
        Assertions.assertEquals(buildSectionRequest().getSectionConversationalName(),
                actualSectionDTO.getSectionConversationalName());
    }

    private SectionCreateRequest buildSectionRequest() {
        SectionCreateRequest sectionCreateRequest = new SectionCreateRequest();
        sectionCreateRequest.setSectionFullName("TestFullName");
        sectionCreateRequest.setSectionShortName("TestShortName");
        sectionCreateRequest.setSectionConversationalName("TestConversationalName");
        return sectionCreateRequest;
    }

    private SectionUpdateRequest buildUpdateSectionRequest() {
        SectionUpdateRequest sectionUpdateRequest = new SectionUpdateRequest();
        sectionUpdateRequest.setSectionId(sectionDTO.getId());
        sectionUpdateRequest.setSectionFullName("NewFullName");
        sectionUpdateRequest.setSectionShortName("NewShortName");
        sectionUpdateRequest.setSectionConversationalName("NewConversationalName");
        return sectionUpdateRequest;
    }
}
