package com.chemax.project.service;

import com.chemax.project.dto.EquipmentTypeDTO;
import com.chemax.project.entities.EquipmentTypeEntity;
import com.chemax.project.exceptions.EntityNotFoundException;
import com.chemax.project.exceptions.NeedToMoveEntityException;
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
    private final EquipmentService equipmentService;

    public EquipmentTypeService(EquipmentTypeEntityRepository equipmentTypeRepository,
                                EquipmentService equipmentService) {
        this.equipmentTypeRepository = equipmentTypeRepository;
        this.equipmentService = equipmentService;
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

    public List<EquipmentTypeDTO> getAllEquipmentTypeDTOs() {
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
        EquipmentTypeEntity equipmentTypeEntityToDelete = equipmentTypeRepository.getReferenceById(id);
        if (equipmentTypeEntityToDelete.getEquipmentList().isEmpty()) {
            equipmentTypeRepository.delete(getEquipmentTypeEntity(id));
        } else {
            throw new NeedToMoveEntityException();
        }
    }

    public void updateEquipmentTypeEntity (EquipmentTypeDTO equipmentTypeDTO, Integer id) {
        EquipmentTypeEntity equipmentTypeEntity = equipmentTypeRepository.getReferenceById(id);
        equipmentTypeEntity.setMachineType(Optional.ofNullable(equipmentTypeDTO.getMachineType())
                .orElse(equipmentTypeEntity.getMachineType()));
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
        equipmentTypeDTO.setEquipmentDTOList(equipmentService.getAllEquipmentDTOs().stream()
                .filter(equipmentDTO -> Objects.equals(equipmentDTO.getMachineTypeId(), equipmentTypeEntityToConvert
                        .getId())).collect(Collectors.toList()));
        return equipmentTypeDTO;
    }
}
