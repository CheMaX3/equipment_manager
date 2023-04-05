package com.chemax.project.service;


import com.chemax.project.dto.EquipmentTypeDTO;
import com.chemax.project.entity.EquipmentType;
import com.chemax.project.exception.EntityNotFoundException;
import com.chemax.project.repository.EquipmentTypeRepository;
import com.chemax.project.request.EquipmentTypeCreateRequest;
import com.chemax.project.request.EquipmentTypeUpdateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EquipmentTypeServiceImpl implements EquipmentTypeService {

    private final EquipmentTypeRepository equipmentTypeRepository;
    private final EquipmentServiceImpl equipmentService;

    public EquipmentTypeServiceImpl(EquipmentTypeRepository equipmentTypeRepository,
                                    EquipmentServiceImpl equipmentService) {
        this.equipmentTypeRepository = equipmentTypeRepository;
        this.equipmentService = equipmentService;
    }

    public EquipmentTypeDTO createEquipmentType(EquipmentTypeCreateRequest equipmentTypeCreateRequest) {
        EquipmentTypeDTO equipmentTypeDTO = new EquipmentTypeDTO();
        try {
            EquipmentType equipmentType = equipmentTypeRepository
                    .save(buildEquipmentTypeFromRequest(equipmentTypeCreateRequest));
            equipmentTypeDTO = convertEquipmentTypeToDTO(equipmentType);
        }catch (Exception ex) {
            log.error("Can't save object " + equipmentTypeCreateRequest.toString());
        }
        return equipmentTypeDTO;
    }

    public List<EquipmentTypeDTO> getAllEquipmentTypeDTOs() {
        List<EquipmentTypeDTO> equipmentTypeDTOListFromDB = new ArrayList<>();
        try {
            equipmentTypeDTOListFromDB = equipmentTypeRepository.findAll().stream()
                    .map(this::convertEquipmentTypeToDTO)
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            log.error("Can't retrieve objects from DB");
        }
        return equipmentTypeDTOListFromDB;
    }

    public boolean equipmentInclusionCheck(Integer equipmentTypeId) {
        boolean equipmentTypeIncludesEquipment = false;
        try {
            equipmentTypeIncludesEquipment = equipmentService
                    .getEquipmentListByEquipmentTypeId(equipmentTypeId).isEmpty();
        } catch (Exception ex) {
            log.error("Can't check equipment objects inclusion in equipmentType with id=" + equipmentTypeId);
        }
        return equipmentTypeIncludesEquipment;
    }

    public void deleteEquipmentType(Integer equipmentTypeId) {
        try {
            equipmentTypeRepository.delete(equipmentTypeRepository.getReferenceById(equipmentTypeId));
        } catch (Exception ex) {
            log.error("Can't delete object with id=" + equipmentTypeId);
        }
    }

    public EquipmentTypeDTO updateEquipmentType(EquipmentTypeUpdateRequest equipmentTypeUpdateRequest) {
        EquipmentTypeDTO equipmentTypeDTO = new EquipmentTypeDTO();
        try {
            EquipmentType equipmentTypeFromDB = equipmentTypeRepository.findById(equipmentTypeUpdateRequest
                            .getEquipmentTypeId()).orElseThrow(EntityNotFoundException::new);
            equipmentTypeFromDB.setMachineType(Optional.ofNullable(equipmentTypeUpdateRequest.getMachineType())
                    .orElse(equipmentTypeFromDB.getMachineType()));
            equipmentTypeRepository.save(equipmentTypeFromDB);
            equipmentTypeDTO = convertEquipmentTypeToDTO(equipmentTypeFromDB);
        } catch (Exception ex) {
            log.error("Can't save object with id=" + equipmentTypeUpdateRequest.getEquipmentTypeId());
        }
        return equipmentTypeDTO;
    }

    public EquipmentTypeDTO getEquipmentTypeDTOById(Integer equipmentTypeId) {
        EquipmentTypeDTO equipmentTypeDTO = new EquipmentTypeDTO();
        try {
            equipmentTypeDTO = convertEquipmentTypeToDTO(equipmentTypeRepository.getReferenceById(equipmentTypeId));
        }
        catch (Exception ex) {
            log.error("Can't retrieve object from DB with id=" + equipmentTypeId);
        }
        return equipmentTypeDTO;
    }

    private EquipmentType buildEquipmentTypeFromRequest(EquipmentTypeCreateRequest equipmentTypeCreateRequest) {
        EquipmentType builtEquipmentType = new EquipmentType();
        builtEquipmentType.setMachineType(equipmentTypeCreateRequest.getMachineType());
        return builtEquipmentType;
    }

    private EquipmentTypeDTO convertEquipmentTypeToDTO(EquipmentType equipmentType) {
        EquipmentTypeDTO equipmentTypeDTO = new EquipmentTypeDTO();
        equipmentTypeDTO.setId(equipmentType.getId());
        equipmentTypeDTO.setMachineType(equipmentType.getMachineType());
        equipmentTypeDTO.setEquipmentDTOList(equipmentType.getEquipmentList().stream()
                .map(equipmentService::convertEquipmentToDTO).collect(Collectors.toList()));
        return equipmentTypeDTO;
    }
}
