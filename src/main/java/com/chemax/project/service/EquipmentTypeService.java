package com.chemax.project.service;

import com.chemax.project.dto.EquipmentTypeDTO;
import com.chemax.project.entity.EquipmentType;
import com.chemax.project.request.EquipmentTypeRequest;

import java.util.List;

public interface EquipmentTypeService {

    EquipmentTypeDTO createEquipmentTypeEntity (EquipmentTypeRequest request);

    EquipmentTypeDTO getEquipmentTypeDTO (Integer id);

    List<EquipmentTypeDTO> getAllEquipmentTypeDTOs();

    List<EquipmentTypeDTO> getEquipmentTypeDTOsByCount (Integer count);

    boolean deleteEquipmentTypeEntity (Integer id);

    void updateEquipmentTypeEntity (EquipmentTypeDTO equipmentTypeDTO, Integer id);

    EquipmentTypeDTO convertEquipmentTypeEntityToDTO (EquipmentType equipmentTypeToConvert);

}
