package com.chemax.project.service;

import com.chemax.project.dto.EquipmentDTO;
import com.chemax.project.request.EquipmentCreateRequest;
import com.chemax.project.request.EquipmentUpdateRequest;

import java.util.List;

public interface EquipmentService {

    EquipmentDTO createEquipment(EquipmentCreateRequest equipmentCreateRequest);

    List<EquipmentDTO> getAllEquipmentDTOs();

    void deleteEquipment(Integer equipmentId);

    EquipmentDTO updateEquipment(EquipmentUpdateRequest equipmentUpdateRequest);

    List<EquipmentDTO> getEquipmentListByAreaId(Integer areaId);

    List<EquipmentDTO> getEquipmentListByEquipmentTypeId(Integer equipmentTypeId);

    EquipmentDTO getEquipmentDTO(Integer id);

}
