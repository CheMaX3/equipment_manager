package com.chemax.project.service;

import com.chemax.project.entity.Section;
import com.chemax.project.request.SectionRequest;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class SectionServiceImplTest {

    SectionServiceImpl mockSectionServiceImpl = mock(SectionServiceImpl.class);

    @Test
    private Section buildSectionEntityFromRequest(SectionRequest request) {
        Section builtSection = new Section();
        builtSection.setSectionFullName(request.getSectionFullName());
        builtSection.setSectionShortName(request.getSectionShortName());
        builtSection.setSectionConversationalName((request.getSectionConversationalName()));
        return builtSection;
    }

}
