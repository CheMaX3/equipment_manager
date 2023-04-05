package com.chemax.project.service;

import com.chemax.project.dto.EquipmentTypeDTO;
import com.chemax.project.request.EquipmentTypeCreateRequest;
import com.chemax.project.request.EquipmentTypeUpdateRequest;

import java.util.List;

public interface EquipmentTypeService {

    EquipmentTypeDTO createEquipmentType(EquipmentTypeCreateRequest equipmentTypeCreateRequest);

    List<EquipmentTypeDTO> getAllEquipmentTypeDTOs();

    boolean equipmentInclusionCheck(Integer equipmentTypeId);

    void deleteEquipmentType(Integer equipmentTypeId);

    EquipmentTypeDTO updateEquipmentType(EquipmentTypeUpdateRequest equipmentTypeUpdateRequest);

    EquipmentTypeDTO getEquipmentTypeDTOById(Integer equipmentTypeId);
}
