//package com.chemax.project.service;
//
//import com.chemax.project.dto.SectionDTO;
//import com.chemax.project.request.SectionRequest;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import static org.mockito.Mockito.mock;
//
//public class SectionServiceImplTest {
//
//    @Autowired
//    private SectionService sectionService;
//
//    public SectionServiceImplTest(SectionService sectionService) {
//        this.sectionService = sectionService;
//    }
//
//
//    @Test
//    void createSectionEntityTest() {
//        SectionRequest sectionRequest = buildSectionRequest();
//        SectionDTO dto = sectionService.createSectionEntity(sectionRequest);
//        assertNotNull(dto.getId());
//        assertEquals(sectionRequest.getSectionFullName(), dto.getSectionFullName());
//    }
//
//    private SectionRequest buildSectionRequest() {
//        SectionRequest sectionRequest = new SectionRequest();
//        sectionRequest.setSectionFullName("123");
//        sectionRequest.setSectionShortName("565");
//        sectionRequest.setSectionConversationalName("gsdfg");
//        return sectionRequest;
//    }
//
//}
