//package com.chemax.project.service;
//
//import com.chemax.project.dto.SectionDTO;
//import com.chemax.project.request.SectionCreateRequest;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.mock;
//
//public class SectionServiceImplTest {
//
//    @MockBean
//    private SectionServiceImpl sectionService;
//
//
//    public SectionServiceImplTest(SectionServiceImpl sectionService) {
//        this.sectionService = sectionService;
//    }
//
//
//    @Test
//    void createSectionEntityTest() {
//        SectionCreateRequest sectionCreateRequest = buildSectionRequest();
//        sectionService.createSection(sectionCreateRequest);
//    }
//
//    private SectionCreateRequest buildSectionRequest() {
//        SectionCreateRequest sectionCreateRequestRequest = new SectionCreateRequest();
//        sectionCreateRequestRequest.setSectionFullName("123");
//        sectionCreateRequestRequest.setSectionShortName("565");
//        sectionCreateRequestRequest.setSectionConversationalName("gsdfg");
//        return sectionCreateRequestRequest;
//    }
//
//    //TODO:прикрутить H2database
//}
