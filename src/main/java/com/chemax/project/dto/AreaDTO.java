package com.chemax.project.dto;

import com.chemax.project.entity.Section;
import lombok.Data;

import java.util.List;

@Data
public class AreaDTO {

    private Integer id;
    private String areaFullName;
    private String areaShortName;
    private String areaConversationalName;
    private Section section;
    private List<EquipmentDTO> equipmentDTOList;
}
