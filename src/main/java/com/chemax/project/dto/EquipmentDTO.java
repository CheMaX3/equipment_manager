package com.chemax.project.dto;

import lombok.Data;

@Data
public class EquipmentDTO {
    private Integer id;
    private String machineModel;
    private String manufacturerCountry;
    private String manufacturer;
    private Integer manufacturingYear;
    private String machineNumber;
    private String details;
    private Integer machineTypeId;
    private Integer areaId;
}
