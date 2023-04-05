package com.chemax.project.dto;

import lombok.Data;

import java.util.List;

@Data
public class EquipmentTypeDTO {

    private Integer id;
    private String machineType;
    private List<EquipmentDTO> equipmentDTOList;
}
