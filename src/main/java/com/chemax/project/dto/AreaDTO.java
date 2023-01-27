package com.chemax.project.dto;

import lombok.Data;

import java.util.List;

@Data
public class AreaDTO {
    private Integer id;
    private String areaFullName;
    private String areaShortName;
    private String areaConversationalName;
    private Integer sectionID;
    private String sectionFullName;
    private List<EquipmentDTO> equipmentDTOList;
}
