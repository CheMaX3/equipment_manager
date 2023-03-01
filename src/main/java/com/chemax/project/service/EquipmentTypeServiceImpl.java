package com.chemax.project.service;

import com.chemax.project.dto.EquipmentTypeDTO;
import com.chemax.project.entity.EquipmentType;
import com.chemax.project.repository.EquipmentTypeRepository;
import com.chemax.project.request.EquipmentTypeRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EquipmentTypeServiceImpl implements EquipmentTypeService {

    private final EquipmentTypeRepository equipmentTypeRepository;
    private final EquipmentServiceImpl equipmentServiceImpl;

    public EquipmentTypeServiceImpl(EquipmentTypeRepository equipmentTypeRepository,
                                    EquipmentServiceImpl equipmentServiceImpl) {
        this.equipmentTypeRepository = equipmentTypeRepository;
        this.equipmentServiceImpl = equipmentServiceImpl;
    }


    public EquipmentTypeDTO createEquipmentTypeEntity (EquipmentTypeRequest request) {
        EquipmentType equipmentType = buildEquipmentTypeEntityFromRequest(request);
        equipmentTypeRepository.save(equipmentType);
        return convertEquipmentTypeEntityToDTO(equipmentType);
    }

    private EquipmentType getEquipmentTypeEntity (Integer id) {
        return equipmentTypeRepository.getReferenceById(id);
    }

    public EquipmentTypeDTO getEquipmentTypeDTO (Integer id) {
        return convertEquipmentTypeEntityToDTO(getEquipmentTypeEntity(id));
    }

    public List<EquipmentTypeDTO> getAllEquipmentTypeDTOs() {
        List<EquipmentTypeDTO> equipmentTypeDTOList = new ArrayList<>();
        for (EquipmentType et: equipmentTypeRepository.findAll()) {
            equipmentTypeDTOList.add(convertEquipmentTypeEntityToDTO(et));
        }
        return equipmentTypeDTOList;
    }

    public List<EquipmentTypeDTO> getEquipmentTypeDTOsByCount (Integer count) {
        List<EquipmentTypeDTO> equipmentTypeDTOList = new ArrayList<>();
        for (EquipmentType et: equipmentTypeRepository.findAll()) {
            equipmentTypeDTOList.add(convertEquipmentTypeEntityToDTO(et));
        }
        return equipmentTypeDTOList.stream().limit(count).collect(Collectors.toList());
    }

    public boolean deleteEquipmentTypeEntity (Integer id) {
        EquipmentType equipmentTypeToDelete = equipmentTypeRepository.getReferenceById(id);
        if (equipmentTypeToDelete.getEquipmentList().isEmpty()) {
            equipmentTypeRepository.delete(getEquipmentTypeEntity(id));
            return true;
        } else {
            return false;
        }
    }

    public void updateEquipmentTypeEntity (EquipmentTypeDTO equipmentTypeDTO, Integer id) {
        EquipmentType equipmentType = equipmentTypeRepository.getReferenceById(id);
        equipmentType.setMachineType(Optional.ofNullable(equipmentTypeDTO.getMachineType())
                .orElse(equipmentType.getMachineType()));
        equipmentTypeRepository.save(equipmentType);
    }

    private EquipmentType buildEquipmentTypeEntityFromRequest(EquipmentTypeRequest request) {
        EquipmentType builtEquipmentType = new EquipmentType();
        builtEquipmentType.setMachineType(request.getMachineType());
        return builtEquipmentType;
    }

    public EquipmentTypeDTO convertEquipmentTypeEntityToDTO (EquipmentType equipmentTypeToConvert) {
        EquipmentTypeDTO equipmentTypeDTO = new EquipmentTypeDTO();
        equipmentTypeDTO.setId(equipmentTypeToConvert.getId());
        equipmentTypeDTO.setMachineType(equipmentTypeToConvert.getMachineType());
        equipmentTypeDTO.setEquipmentDTOList(equipmentServiceImpl.getAllEquipmentDTOs().stream()
                .filter(equipmentDTO -> Objects.equals(equipmentDTO.getMachineTypeId(), equipmentTypeToConvert
                        .getId())).collect(Collectors.toList()));
        return equipmentTypeDTO;
    }

}
