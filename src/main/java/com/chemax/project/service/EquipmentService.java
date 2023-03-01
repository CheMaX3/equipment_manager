package com.chemax.project.service;

import com.chemax.project.dto.EquipmentDTO;
import com.chemax.project.entity.Equipment;
import com.chemax.project.request.EquipmentRequest;

import java.util.List;

public interface EquipmentService {

    EquipmentDTO createEquipmentEntity(EquipmentRequest request);

    EquipmentDTO getEquipmentDTO (Integer id);

    List<EquipmentDTO> getAllEquipmentDTOs();

    List<EquipmentDTO> getEquipmentDTOsByCount (Integer count);

    List<EquipmentDTO> getAllEquipmentSelectedAreaDTOs(Integer id);

    List<EquipmentDTO> getAllEquipmentSelectedMachineTypeDTOs(Integer id);

    void deleteEquipmentEntity (Integer id);

    void updateEquipmentEntity (EquipmentDTO equipmentDTO, Integer id);

    EquipmentDTO convertEquipmentEntityToDTO(Equipment equipment);

}
