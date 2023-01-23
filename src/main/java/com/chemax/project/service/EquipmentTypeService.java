package com.chemax.project.service;

import com.chemax.project.dto.EquipmentDTO;
import com.chemax.project.dto.EquipmentTypeDTO;
import com.chemax.project.entities.EquipmentTypeEntity;
import com.chemax.project.exceptions.EntityNotFoundException;
import com.chemax.project.repository.EquipmentTypeEntityRepository;
import com.chemax.project.request.EquipmentTypeRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EquipmentTypeService {

    private final EquipmentTypeEntityRepository equipmentTypeRepository;
    private final List<EquipmentDTO> equipmentDTOList;

    public EquipmentTypeService(EquipmentTypeEntityRepository equipmentTypeRepository, List<EquipmentDTO> equipmentDTOList) {
        this.equipmentTypeRepository = equipmentTypeRepository;
        this.equipmentDTOList = equipmentDTOList;
    }


    public EquipmentTypeDTO createEquipmentTypeEntity (EquipmentTypeRequest request) {
        EquipmentTypeEntity equipmentTypeEntity = buildEquipmentTypeEntityFromRequest(request);
        equipmentTypeRepository.save(equipmentTypeEntity);
        return convertEquipmentTypeEntityToDTO(equipmentTypeEntity);
    }

    private EquipmentTypeEntity getEquipmentTypeEntity (Integer id) throws EntityNotFoundException {
        return equipmentTypeRepository.getReferenceById(id);
    }

    public EquipmentTypeDTO getEquipmentTypeDTO (Integer id) {
        return convertEquipmentTypeEntityToDTO(getEquipmentTypeEntity(id));
    }

    private List<EquipmentTypeDTO> getAllEquipmentTypeDTOs() {
        List<EquipmentTypeDTO> equipmentTypeDTOList = new ArrayList<>();
        for (EquipmentTypeEntity et: equipmentTypeRepository.findAll()) {
            equipmentTypeDTOList.add(convertEquipmentTypeEntityToDTO(et));
        }
        return equipmentTypeDTOList;
    }

    public List<EquipmentTypeDTO> getEquipmentTypeDTOsByCount (Integer count) {
        List<EquipmentTypeDTO> equipmentTypeDTOList = new ArrayList<>();
        for (EquipmentTypeEntity et: equipmentTypeRepository.findAll()) {
            equipmentTypeDTOList.add(convertEquipmentTypeEntityToDTO(et));
        }
        return equipmentTypeDTOList.stream().limit(count).collect(Collectors.toList());
    }

    public void deleteEquipmentTypeEntity (Integer id) {
        equipmentTypeRepository.delete(getEquipmentTypeEntity(id));
    }

    public void updateEquipmentTypeEntity (EquipmentTypeRequest request, Integer id) {
        EquipmentTypeEntity equipmentTypeEntity = equipmentTypeRepository.getReferenceById(id);
        equipmentTypeEntity.setMachineType(Optional.ofNullable(request.getMachineType()).orElse(equipmentTypeEntity.getMachineType()));
        equipmentTypeRepository.save(equipmentTypeEntity);
    }

    private EquipmentTypeEntity buildEquipmentTypeEntityFromRequest(EquipmentTypeRequest request) {
        EquipmentTypeEntity builtEquipmentTypeEntity = new EquipmentTypeEntity();
        builtEquipmentTypeEntity.setMachineType(request.getMachineType());
        return builtEquipmentTypeEntity;
    }

    public EquipmentTypeDTO convertEquipmentTypeEntityToDTO (EquipmentTypeEntity equipmentTypeEntityToConvert) {
        EquipmentTypeDTO equipmentTypeDTO = new EquipmentTypeDTO();
        equipmentTypeDTO.setId(equipmentTypeEntityToConvert.getId());
        equipmentTypeDTO.setMachineType(equipmentTypeEntityToConvert.getMachineType());
        equipmentTypeDTO.setEquipmentDTOList(equipmentDTOList.stream()
                .filter(equipmentDTO -> Objects.equals(equipmentDTO.getMachineTypeId(), equipmentTypeEntityToConvert.getId())).collect(Collectors.toList()));
        return equipmentTypeDTO;
    }
}
