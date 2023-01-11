package com.chemax.project.service;

import com.chemax.project.entities.SectionEntity;
import com.chemax.project.exceptions.EntityNotFoundException;
import com.chemax.project.request.SectionRequest;

public class StubService {
    public SectionEntity createSectionEntity(SectionRequest request) {
        return build();
    }

    public SectionEntity getSectionEntity(Integer id) throws EntityNotFoundException {
        return build();
    }

    private SectionEntity buildSectionEntityFromRequest(SectionRequest request) {
        SectionEntity builtSectionEntity = new SectionEntity();
        builtSectionEntity.setSectionFullName(request.getSectionFullName());
        builtSectionEntity.setSectionShortName(request.getSectionShortName());
        builtSectionEntity.setSectionConversationalName((request.getSectionConversationalName()));
        return builtSectionEntity;
    }

    private SectionEntity build() {
        SectionEntity sectionEntity = new SectionEntity();
        sectionEntity.setId(42);
        sectionEntity.setSectionShortName("shortName");
        sectionEntity.setSectionFullName("fullName");
        sectionEntity.setSectionConversationalName("conversName");

        return sectionEntity;
    }
}
