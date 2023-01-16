package com.chemax.project.dto;

import com.chemax.project.entities.SectionEntity;
import lombok.Data;

@Data
public class AreaDTO {
    private Integer id;
    private String areaFullName;
    private String areaShortName;
    private String areaConversationalName;
    private Integer sectionID;
    private SectionEntity sectionEntity;
}
