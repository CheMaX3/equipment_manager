package com.chemax.project.dto;

import com.chemax.project.entity.Area;
import com.chemax.project.entity.Document;
import com.chemax.project.entity.EquipmentType;
import lombok.Data;

import java.util.List;

@Data
public class EquipmentDTO {

    private Integer id;
    private String machineModel;
    private String manufacturerCountry;
    private String manufacturer;
    private String manufacturingYear;
    private String machineNumber;
    private String details;
    private EquipmentType equipmentType;
    private Area area;
    private List<Document> files;
}
