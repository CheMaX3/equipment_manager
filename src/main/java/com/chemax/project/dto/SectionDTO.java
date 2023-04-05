package com.chemax.project.dto;

import lombok.Data;

import java.util.List;

@Data
public class SectionDTO {

    private Integer id;
    private String sectionFullName;
    private String sectionShortName;
    private String sectionConversationalName;
    private List<AreaDTO> areaDTOList;
}
